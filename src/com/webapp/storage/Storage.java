package com.webapp.storage;

import com.webapp.model.Resume;


public interface Storage {

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    void clear();

    Resume[] getAll();

    int size();

    int getIndex(String uuid);

    int getCapacity();
}
