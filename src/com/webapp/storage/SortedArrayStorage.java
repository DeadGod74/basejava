package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {

    }

    @Override
    public Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return List.of();
    }

    @Override
    public int getIndex(String uuid) {
        return -1;
    }
}