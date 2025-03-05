package com.webapp.storage;

import com.webapp.exception.StorageException;
import org.junit.Assert;
import org.junit.Before;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        storage.get("dummy");
    }

    @org.junit.Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @org.junit.Test
    public void delete() {
        storage.delete(UUID_1);
        System.out.println("Size after deletion: " + storage.size());
        assertSize(2);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @org.junit.Test
    public void update() {
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        assertGet(updatedResume);
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
    }

    @org.junit.Test
    public void getAll() {
        Resume[] expected = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);

    }

    @org.junit.Test
    public void getIndex() {
        int index = storage.getIndex(UUID_2);
        Assert.assertTrue(index >= 0);
        assertGet(storage.get(UUID_2));
        int notExistIndex = storage.getIndex("dummy");
        assertEquals(-1, notExistIndex);
    }

    @org.junit.Test
    public void fillStorage() {
        int capacity = storage.getCapacity();
        for (int i = 0; i < capacity; i++) {
            storage.save(new Resume("uuid" + i));
        }
        assertSize(capacity);
    }

    @org.junit.Test(expected = StorageException.class)
    public void saveOverflow() {
        int capacity = storage.getCapacity();
        for (int i = 0; i < capacity; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("overflowUuid"));
    }
}