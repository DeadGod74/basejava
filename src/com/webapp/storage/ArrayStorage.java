package src.com.webapp.storage;

import src.com.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[100];
    private int size = 0;

    public void save(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                System.out.println("Resume with UUID " + resume.getUuid() + "already exist");
                return;
            }
        }

        if (size < storage.length) {
            storage[size++] = resume;
        } else {
            System.out.println("Storage is full. Cannot save more resumes.");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                return;
            }
        }
        System.out.println("Resume with UUID " + resume.getUuid() + " not found for update.");
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[--size];
                storage[size] = null;
                return;
            }
        }
        System.out.println("src.com.webapp.model.Resume not found for deletion.");
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
