package src.com.webapp.storage;

import src.com.webapp.exception.ExistStorageException;
import src.com.webapp.exception.NotExistStorageException;
import src.com.webapp.exception.StorageException;
import src.com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public final void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0){
            throw new ExistStorageException(resume.getUuid());
        }
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full. Cannot save more resumes.", resume.getUuid());
        }
        doSave(resume, getIndex(resume.getUuid()));
        size++;
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            // Удаляем элемент, сдвигая элементы массива
            for (int i = index; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null; // Обнуляем последний элемент
            size--; // Уменьшаем размер
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            doUpdate(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public abstract int getIndex(String uuid);

    protected abstract void doSave(Resume resume, int index);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doDelete(int index);

}
