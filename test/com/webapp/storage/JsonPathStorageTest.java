package com.webapp.storage;

import com.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {super(new PathStorage(FILE_PATH.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
