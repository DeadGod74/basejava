package com.webapp.exception;

public class StorageException extends RuntimeException {
    final private String uuid;


    public StorageException(String message) {
        super(message);
        this.uuid = null;
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
