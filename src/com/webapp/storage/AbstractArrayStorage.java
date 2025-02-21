package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int storage_limit = 10000;

    final Resume[] storage = new Resume[storage_limit];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume" + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public abstract int getIndex(String uuid);

    protected void doSave(Resume resume, int index) {
        storage[size++] = resume;
    }

    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    protected void doDelete(int index) {
        storage[index] = storage[--size];
        storage[size] = null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
}
