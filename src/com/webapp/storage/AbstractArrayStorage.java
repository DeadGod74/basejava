package com.webapp.storage;
import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public int getCapacity() {
        return STORAGE_LIMIT;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    public void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }


    @Override
    public boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final List<Resume> getAll() {
        List<Resume> resumes = new ArrayList<>(size); // Создаем новый список
        for (int i = 0; i < size; i++) {
            resumes.add(storage[i]);
        }
        Collections.sort(resumes);
        return resumes;
    }

    public final List<Resume> getAllSorted() {
        List<Resume> resumes = getAll();
        Collections.sort(resumes);
        return resumes;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    public abstract Integer getSearchKey(String uuid);

}
