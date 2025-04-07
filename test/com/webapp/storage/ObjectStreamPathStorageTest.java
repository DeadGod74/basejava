package com.webapp.storage;

import java.io.File;

import static org.junit.Assert.*;
public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super((Storage) new ObjectStreamPathStorage(new File(FILE_PATH.getAbsolutePath())));
    }
}