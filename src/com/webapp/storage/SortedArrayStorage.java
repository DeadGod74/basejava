package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void doSave(Resume resume, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - insertIndex);
        storage[index] = resume;
    }


    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }


    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    @Override
    public int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}