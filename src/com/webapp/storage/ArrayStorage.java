package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Objects;

public class ArrayStorage extends AbstractArrayStorage{

    public int getIndex(String uuid) {
        for (int i=0; i < size; i++) {
            if (Objects.equals(uuid, storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected void doSave(Resume resume, int index) {
        storage[size] = resume;
    }

    protected void doDelete(int index) {
        storage[index] = storage[--size];
        storage[size] = null;
    }

    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

}
