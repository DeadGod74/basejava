package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    public abstract Object getSearchKey(String uuid);

    protected abstract Resume doGet(Object searchKey);

    public abstract boolean isExist(Object searchKey);

}
