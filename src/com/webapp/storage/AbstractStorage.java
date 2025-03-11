package com.webapp.storage;

import com.webapp.exception.ExistStorageException;
import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int index = getIndexAndCheckNotExistence(uuid);
        return doGet(index);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        checkExistence(resume.getUuid(), index);
        doSave(resume, index);
    }

    public final void delete(String uuid) {
        int index = getIndexAndCheckNotExistence(uuid);
        doDelete(index);
    }

    public void update(Resume resume) {
        int index = getIndexAndCheckNotExistence(resume.getUuid());
        doUpdate(resume, index);
    }
    private int getIndexAndCheckNotExistence(String uuid) {
        int index = getIndex(uuid);
        checkNotExistence(uuid, index);
        return index;
    }

    private void checkNotExistence(String uuid, int index) {
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void checkExistence(String uuid, int index) {
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doDelete(int index);

    protected abstract Resume doGet(int index);

    public abstract int getIndex(String uuid);

}
