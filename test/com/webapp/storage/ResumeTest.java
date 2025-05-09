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

            for (TypeSection typeSection : TypeSection.values()) {
                Section section = resume.getSections().get(typeSection);
                if (section != null) {
                    System.out.println(typeSection + ": " + section);
                }
            }
        }

        assertEquals("1", resumes.get(0).getUuid());
        assertEquals("John Doe", resumes.get(0).getFullName());
        assertEquals("+1234567890", resumes.get(0).getContacts().get(ContactType.PHONE));
        assertEquals("johndoe@example.com", resumes.get(0).getContacts().get(ContactType.MAIL));

        Section personalSection = resumes.get(0).getSections().get(TypeSection.PERSONAL);
        assertTrue(personalSection instanceof TextSection);
        assertEquals("Ответственный, коммуникабельный", ((TextSection) personalSection).getText());

        Section objectiveSection = resumes.get(0).getSections().get(TypeSection.OBJECTIVE);
        assertTrue(objectiveSection instanceof TextSection);
        assertEquals("Получение должности разработчика", ((TextSection) objectiveSection).getText());

        List<Company> experiences = resumes.get(0).getSections().get(TypeSection.EXPERIENCE).getContent();
        for (Company organization : experiences) {
            for (Period period : organization.getPeriods()) {
                LocalDate startDate = period.getStartDate();
                LocalDate endDate = period.getEndDate();
                assertFalse(endDate.isBefore(startDate),
                        String.format("End date %s is before start date %s for period %s at %s",
                                endDate, startDate, period.getName(), organization.getNameCompany()));
            }
        }

        List<Company> educations = resumes.get(0).getSections().get(TypeSection.EDUCATION).getContent();
        for (Company organization : educations) {
            for (Period period : organization.getPeriods()) {
                LocalDate startDate = period.getStartDate();
                LocalDate endDate = period.getEndDate();
                assertFalse(endDate.isBefore(startDate),
                        String.format("End date %s is before start date %s for degree %s at %s",
                                endDate, startDate, period.getName(), organization.getNameCompany()));
            }
        }
        inputStream.close();
    }
}