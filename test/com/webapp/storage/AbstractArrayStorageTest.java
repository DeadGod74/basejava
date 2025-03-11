package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.StorageException;
import org.junit.Assert;
import org.junit.Before;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

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


    protected AbstractArrayStorageTest(Storage storage) {
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

    @org.junit.Test
    public void size() {
        assertSize(3);
    }

    @org.junit.Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(dummy);
    }

    @org.junit.Test
    public void save() throws Exception{
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @org.junit.Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @org.junit.Test
    public void delete() throws Exception{
        storage.delete(RESUME_1.getUuid());
        System.out.println("Size after deletion: " + storage.size());
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(dummy);
    }

    @org.junit.Test
    public void update() throws Exception{
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        assertGet(updatedResume);
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get(dummy);
    }

    @org.junit.Test
    public void clear() throws Exception{
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
    }

    @org.junit.Test
    public void getAll() throws Exception{
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);

    }

    @org.junit.Test
    public void getIndex() throws Exception{
        int index = storage.getIndex(UUID_2);
        Assert.assertTrue(index >= 0);
        assertGet(storage.get(UUID_2));
        int notExistIndex = storage.getIndex(dummy);
        assertEquals(-1, notExistIndex);
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

    @org.junit.Test(expected = StorageException.class)
    public void saveOverflow() throws Exception{
        int capacity = 3;
        for (int i = 0; i < capacity; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("overflowUuid"));
    }

}