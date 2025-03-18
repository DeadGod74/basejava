package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    public int getIndex(String uuid) {
        return -1;
    }

    @Override
    public int getCapacity() {
        return storage.length;
    }

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1; 
    }

    @Override
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
        storage[size] = resume;
        size++;
    }
}
