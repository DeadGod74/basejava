package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.List;

import static org.junit.Assert.*;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super((Storage) new ObjectStreamStorage(FILE_PATH) {
            @Override
            public void save(Resume resume) {

            }

            @Override
            public Resume get(String uuid) {
                return null;
            }

            @Override
            public void update(Resume resume) {

            }

            @Override
            public void delete(String uuid) {

            }

            @Override
            public void clear() {

            }

            @Override
            public List<Resume> getAll() {
                return List.of();
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public List<Resume> getAllSorted() {
                return List.of();
            }
        });
    }
}