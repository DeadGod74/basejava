package com.webapp.storage;

import com.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    public int getIndex(String uuid) {
        return 0;
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
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {

    }

    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
