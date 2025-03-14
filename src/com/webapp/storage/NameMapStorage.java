package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.*;

public class NameMapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    public String getSearchKey(String name) {
        for (Resume resume : map.values()) {
            if (resume.getFullName().equalsIgnoreCase(name)) {
                return resume.getUuid();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> sortedList = new ArrayList<>(map.values());
        Collections.sort(sortedList);
        return sortedList;
    }

    public List<Resume> getAllSorted() {
        return map.values().stream()
                .sorted(Comparator.comparing(Resume::getFullName)
                        .thenComparing(Resume::getUuid))
                .toList(); 
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public int getIndex(String uuid) {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}