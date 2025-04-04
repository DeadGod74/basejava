package com.webapp.storage;

import com.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class AbstractFileStorageTest {

    private AbstractFileStorage storage;
    private File tempDir;

    @Before
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("storageTest").toFile();
        storage = new AbstractFileStorage(tempDir) {};
    }

    @After
    public void tearDown() {
        // Удаляем временную директорию после тестов
        for (File file : tempDir.listFiles()) {
            file.delete();
        }
        tempDir.delete();
    }

    @Test
    public void testClear() {
        createTestFile("resume1");
        createTestFile("resume2");
        assertEquals(2, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testSaveAndGet() {
        Resume resume = new Resume("uuid123");
        File file = storage.getSearchKey(resume.getUuid());
        storage.doSave(resume, file);
        Resume retrievedResume = storage.doGet(file);
        assertNotNull(retrievedResume);
        assertEquals(resume.getUuid(), retrievedResume.getUuid());
    }

    @Test
    public void testDelete() {
        Resume resume = new Resume("uuid123");
        File file = storage.getSearchKey(resume.getUuid());
        storage.doSave(resume, file);
        storage.doDelete(file);
        assertFalse(file.exists());
    }

    @Test
    public void testSize() {
        assertEquals(0, storage.size());
        createTestFile("resume1");
        assertEquals(1, storage.size());
    }

    private void createTestFile(String fileName) {
        try {
            File file = new File(tempDir, fileName);
            file.createNewFile();
        } catch (IOException e) {
            fail("Failed to create test file: " + e.getMessage());
        }
    }
}