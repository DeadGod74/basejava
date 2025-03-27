package com.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/deadgod/IdeaProjects/basejava/test/resources/ResumeTestData.json");
        System.out.println(file.getCanonicalFile());
        File dir = new File("./src/com/webapp");
        System.out.println(dir.isDirectory());
        for(String name: dir.list()) {
            System.out.println(name);
        }
        FileInputStream fis = new FileInputStream(file);
        System.out.println(fis.read());
        fis.close();
    }
}
