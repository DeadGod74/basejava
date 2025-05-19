package com.webapp.sql;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String query, SqlExecutor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return executor.execute(ps);
        } catch (PSQLException e) {
            // Обработка специфичных ошибок PostgreSQL
            if ("23505".equals(e.getSQLState())) { // Код состояния для дублирования ключа
                throw new ExistStorageException("Duplicate key error", e);
            }
            throw new StorageException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement ps) throws SQLException;
    }
}
