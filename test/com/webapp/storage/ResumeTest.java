package com.webapp.storage;

import com.webapp.model.*;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ResumeTest {

    @Test
    public void testDeserializeResumes() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = "/Users/deadgod/IdeaProjects/basejava/test/resources/ResumeTestData.json";
        InputStream inputStream = new FileInputStream(filePath);
        List<Resume> resumes = objectMapper.readValue(inputStream, new TypeReference<List<Resume>>() {});
        assertEquals(2, resumes.size());

        for (Resume resume : resumes) {
            System.out.println("UUID: " + resume.getUuid());
            System.out.println("Full Name: " + resume.getFullName());
            System.out.println("Contacts: " + resume.getContacts());

            for (SectionType sectionType : SectionType.values()) {
                Section section = resume.getSections().get(sectionType);
                if (section != null) {
                    System.out.println(sectionType + ": " + section);
                }
            }
        }

        assertEquals("1", resumes.get(0).getUuid());
        assertEquals("John Doe", resumes.get(0).getFullName());
        assertEquals("+1234567890", resumes.get(0).getContacts().get(ContactType.PHONE));
        assertEquals("johndoe@example.com", resumes.get(0).getContacts().get(ContactType.MAIL));

        Section personalSection = resumes.get(0).getSections().get(SectionType.PERSONAL);
        assertTrue(personalSection instanceof SectionText);
        assertEquals("Ответственный, коммуникабельный", ((SectionText) personalSection).getText());

        Section objectiveSection = resumes.get(0).getSections().get(SectionType.OBJECTIVE);
        assertTrue(objectiveSection instanceof SectionText);
        assertEquals("Получение должности разработчика", ((SectionText) objectiveSection).getText());


        List<Organization> experiences = resumes.get(0).getSections().get(SectionType.EXPERIENCE).getContent();
        for (Organization organization : experiences) {
            for (Position position : organization.getPositions()) {
                LocalDate startDate = position.getStartDate();
                LocalDate endDate = position.getEndDate();
                assertFalse(endDate.isBefore(startDate),
                        String.format("End date %s is before start date %s for position %s at %s",
                                endDate, startDate, position.getTitle(), organization.getHomePage().getName()));
            }
        }

        List<Organization> educations = resumes.get(0).getSections().get(SectionType.EDUCATION).getContent();
        for (Organization organization : educations) {
            for (Position position : organization.getPositions()) {
                LocalDate startDate = position.getStartDate();
                LocalDate endDate = position.getEndDate();
                assertFalse(endDate.isBefore(startDate),
                        String.format("End date %s is before start date %s for degree %s at %s",
                                endDate, startDate, position.getTitle(), organization.getHomePage().getName()));
            }
        }
        inputStream.close();
    }
}