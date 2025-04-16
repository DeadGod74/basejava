package com.webapp.storage;

import com.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest{
    public XmlPathStorageTest() {super(new PathStorage(FILE_PATH.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
