package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest  {

    protected final Storage storage;
    protected static final File FILE_PATH = new File("/Users/deadgod/IdeaProjects/basejava/storage");
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    private final String dummy = "dummy";

    static {
        RESUME_1 = new Resume(UUID_1, "Alice Smith");
        RESUME_2 = new Resume(UUID_2, "Bob Johnson");
        RESUME_3 = new Resume(UUID_3, "Charlie Brown");
        RESUME_4 = new Resume(UUID_4, "Diana Prince");

        RESUME_1.setContact(ContactType.MAIL, "alice.smith@example.com");
        RESUME_1.setContact(ContactType.PHONE, "+9876543210");
        RESUME_1.setSection(TypeSection.OBJECTIVE, new TextSection("Seeking a challenging position in software development."));
        RESUME_1.setSection(TypeSection.PERSONAL, new TextSection("Passionate about technology and innovation."));
        RESUME_1.setSection(TypeSection.ACHIEVEMENT, new ListSection("Developed a successful app", "Led a team of engineers", "Published a research paper"));
        RESUME_1.setSection(TypeSection.QUALIFICATIONS, new ListSection("Python", "HTML/CSS", "Project Management"));

        RESUME_1.setSection(TypeSection.EXPERIENCE,
                new CompanySection(
                        new Company("Tech Solutions Inc.", "http://techsolutions.com",
                                new Period(LocalDate.of(2012, Month.FEBRUARY, 1), LocalDate.of(2018, Month.APRIL, 30), "Senior Developer", "Worked on various projects using modern technologies."),
                                new Period(LocalDate.of(2009, Month.JUNE, 1), LocalDate.of(2012, Month.JANUARY, 31), "Junior Developer", "Assisted in developing web applications."))));

        RESUME_1.setSection(TypeSection.EDUCATION,
                new CompanySection(
                        new Company("University of Technology", "http://universityoftechnology.edu",
                                new Period(LocalDate.of(2005, Month.SEPTEMBER, 1), LocalDate.of(2009, Month.MAY, 31), "Bachelor's Degree in Computer Science", null),
                                new Period(LocalDate.of(2010, Month.OCTOBER, 1), LocalDate.of(2012, Month.JUNE, 30), "Master's Degree in Software Engineering", "Graduated with honors"))
                ));

        RESUME_2.setContact(ContactType.SKYPE, "bob.johnson_skype");
        RESUME_2.setContact(ContactType.PHONE, "+1122334455");
        RESUME_2.setSection(TypeSection.EXPERIENCE,
                new CompanySection(
                        new Company("Global Tech Corp.", "http://globaltechcorp.com",
                                new Period(LocalDate.of(2016, Month.MARCH, 1), LocalDate.now(), "Project Manager", "Managed multiple software development projects."))));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAll() throws Exception{
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(expected);
        List<Resume> actual = storage.getAllSorted();
        assertEquals(expected, actual);
    }


    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume(RESUME_1.getUuid());
        System.out.println(updatedResume);
        updatedResume.setName("Updated Name");
        storage.update(updatedResume);
        assertGet(updatedResume);
    }

    @Test
    public void doSave() {
        storage.delete(RESUME_1.getUuid());
        System.out.println("Size after deletion: " + storage.size());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        System.out.println("Current size before saving: " + storage.size());
        storage.save(RESUME_1);
        storage.save(RESUME_1);
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(dummy);
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(dummy);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test (expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get(dummy);
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        assertSize(0);
        List<Resume> expected = new ArrayList<>();
        List<Resume> actual = storage.getAll();
        assertEquals(expected, actual);
    }

}