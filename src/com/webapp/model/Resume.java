package com.webapp.model;

import java.util.*;

public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {}

    // Конструктор с полным именем
    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    // Конструктор с uuid и полным именем
    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    // Получение контакта по типу
    public String getContact(ContactType contact) {
        return contacts.get(contact);
    }

    // Получение секции по типу
    public Section getSection(SectionType section) {
        return sections.get(section);
    }

    // Установка контакта
    public void setContact(ContactType contactType, String contactValue) {
        contacts.put(contactType, contactValue);
    }

    // Установка секции
    public void setSection(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    // Получение всех контактов
    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    // Получение всех секций
    public Map<SectionType, Section> getSections() {
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

    // Геттеры и сеттеры
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