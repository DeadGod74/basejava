package com.webapp.model;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public int compareTo(Resume other) {
        return this.fullName.compareTo(other.fullName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void SetUuid (String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Resume{ uuid=" + uuid + ", fullName=" + fullName + " }";
    }

    public void setName(String updatedName) {
    }
}