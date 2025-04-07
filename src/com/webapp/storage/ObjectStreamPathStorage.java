package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;

import java.io.*;
import java.util.List;

public class ObjectStreamPathStorage implements Storage, Serialization {

    public ObjectStreamPathStorage(File file) {
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<Resume> getAll() {
        return List.of();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int getIndex(String uuid) {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        return List.of();
    }
}