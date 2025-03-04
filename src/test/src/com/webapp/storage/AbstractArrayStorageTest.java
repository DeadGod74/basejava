package src.com.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import src.com.webapp.exception.NotExistStorageException;
import src.com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {


    protected Storage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }


    @org.junit.Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test
    public void get() {
        Resume resume = storage.get(UUID_1);
        Assert.assertNotNull(resume);
        Assert.assertEquals(UUID_1, resume.getUuid());
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @org.junit.Test
    public void save() {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(newResume, storage.get("uuid4"));
    }

    @org.junit.Test
    public void delete() {
        storage.delete(UUID_1);
        System.out.println("Size after deletion: " + storage.size());
        Assert.assertEquals(2, storage.size());
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @org.junit.Test
    public void update() {
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        Assert.assertEquals(updatedResume, storage.get(UUID_1));
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @org.junit.Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(3, resumes.length);
        Assert.assertTrue(Arrays.asList(resumes).contains(storage.get(UUID_1)));
        Assert.assertTrue(Arrays.asList(resumes).contains(storage.get(UUID_2)));
        Assert.assertTrue(Arrays.asList(resumes).contains(storage.get(UUID_3)));
    }

    @org.junit.Test
    public void getIndex() {
        int index = storage.getIndex(UUID_2);
        Assert.assertTrue(index >= 0);
        Resume retrievedResume = storage.get(UUID_2);
        Assert.assertEquals(UUID_2, retrievedResume.getUuid());
        int notExistIndex = storage.getIndex("dummy");
        Assert.assertEquals(-1, notExistIndex);
    }
}