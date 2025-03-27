package com.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/deadgod/IdeaProjects/basejava/test/resources/ResumeTestData.json");
        System.out.println(file.getCanonicalFile());

        File dir = new File("./src/com/webapp");
        System.out.println(dir.isDirectory());

        if (dir.isDirectory()) {
            System.out.println("Contents of the directory:");
            listFilesRecursively(dir);
        }

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {
            System.out.println("Reading file:");
            int data;
            while ((data = isr.read()) != -1) {
                System.out.print((char) data);
            }
        }
    }

    private static void listFilesRecursively(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("Directory: " + file.getAbsolutePath());
                    listFilesRecursively(file);
                } else {
                    System.out.println("File: " + file.getAbsolutePath());
                }
            }
        }
    }
}
