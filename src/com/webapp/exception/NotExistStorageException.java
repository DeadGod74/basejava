package com.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume" + uuid + " not exists", uuid);
    }
}
