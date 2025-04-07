package com.webapp.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    final private String uuid;


    public StorageException(String message, String name, IOException e) {
        super(message);
        this.uuid = null;
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
