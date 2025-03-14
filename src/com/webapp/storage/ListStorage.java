package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{

    private List<Resume> list = new ArrayList<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        list.add(resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        int index = (Integer) searchKey;
        list.set(index, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (Integer) searchKey;
        list.remove(index);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = (Integer) searchKey;
        return list.get(index);
    }

    @Override
    public boolean isExist(Object searchKey) {
        if (searchKey instanceof Integer) { // Проверяем тип searchKey
            int index = (Integer) searchKey; // Приводим к Integer
            return index >= 0 && index < list.size(); // Проверяем существование индекса
        }
        return false;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public int getIndex(String uuid) {
        return 0;
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }
}
