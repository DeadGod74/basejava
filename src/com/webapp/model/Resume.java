package com.webapp.model;

import java.util.*;

public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<TypeSection, Section> sections = new EnumMap<>(TypeSection.class);

    public Resume() {}
    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContact(ContactType contact) {
        return contacts.get(contact);
    }

    public Section getSection(TypeSection section) {
        return sections.get(section);
    }

    public void setContact(ContactType contactType, String contactValue) {
        contacts.put(contactType, contactValue);
    }

    public void setSection(TypeSection typeSection, Section section) {
        sections.put(typeSection, section);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<TypeSection, Section> getSections() {
        return sections;
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Resume{ uuid=" + uuid + ", fullName=" + fullName + " }";
    }

    public void setName(String updatedName) {
        this.fullName = updatedName;
    }

    public String getName() {
        return fullName;
    }
}