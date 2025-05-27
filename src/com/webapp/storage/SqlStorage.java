package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.model.ContactType;
import com.webapp.model.Resume;
import com.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    public Resume get(String uuid) {
        Resume resume = sqlHelper.execute("SELECT * FROM resume WHERE uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new NotExistStorageException(uuid);
                        }
                        return new Resume(uuid, rs.getString("full_name"));
                    }
                });

        sqlHelper.execute("SELECT * FROM contact WHERE resume_uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            addContact(rs, resume);
                        }
                    }
                    return null;
                });

        return resume;
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            updateResume(conn, resume);
            deleteContacts(conn, resume);
            insertContact(conn, resume);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) " +
                    "VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContact(conn, resume);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume " +
                "WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> map = new LinkedHashMap<>();

        List<Resume> resumes = sqlHelper.execute("SELECT * FROM resume ORDER BY full_name, uuid",
                ps -> {
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            String uuid = rs.getString("uuid");
                            Resume resume = new Resume(uuid, rs.getString("full_name"));
                            map.put(uuid, resume);
                        }
                    }
                    return new ArrayList<>(map.values());
                });

        if (!resumes.isEmpty()) {
            String uuids = resumes.stream()
                    .map(Resume::getUuid)
                    .collect(Collectors.joining("','", "'", "'"));

            sqlHelper.execute("SELECT * FROM contact WHERE resume_uuid IN (" + uuids + ")",
                    ps -> {
                        try (ResultSet rs = ps.executeQuery()) {
                            while (rs.next()) {
                                String uuid = rs.getString("resume_uuid");
                                Resume resume = map.get(uuid);
                                if (resume != null) {
                                    addContact(rs, resume);
                                }
                            }
                        }
                        return null;
                    });
        }

        return resumes;
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        });
    }

    private void insertContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) " +
                "VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact " +
                "WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            try {
                ContactType type = ContactType.valueOf(rs.getString("type"));
                resume.addContact(type, value);
            } catch (IllegalArgumentException e) {
                System.err.println("Неверный тип контакта: " + rs.getString("type"));
            }
        }
    }

    private void updateResume(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE resume " +
                "SET full_name = ? " +
                "WHERE uuid = ?")) {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() != 1) {
                throw new NotExistStorageException(resume.getUuid());
            }
        }
    }
}