package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest  {

    protected final Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);
    protected static final Resume RESUME_4 = new Resume(UUID_4);
    private final String dummy = "dummy";

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

    @org.junit.Test
    public void fillStorage() throws Exception{
        storage.clear();
        int capacity = storage.getCapacity();
        System.out.println("Filling storage with capacity: " + capacity);
        int maxIterations = 100; // Максимальное количество итераций для отладки
        int iterations = Math.min(capacity, maxIterations);
        for (int i = 0; i < iterations; i++) {
            System.out.println("Saving resume with uuid: " + i);
            storage.save(new Resume("uuid" + i));
        }
        assertSize(iterations);
        System.out.println("Finished filling storage.");

    }
}