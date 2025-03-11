package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Objects;

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    public int getCapacity() {
        return storage.length;
    }

    protected void doSave(Resume resume, int index) {
        storage[size] = resume;
        size++;
    }

    protected void doDelete(int index) {
        storage[index] = storage[--size];
        storage[size] = null;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

}
