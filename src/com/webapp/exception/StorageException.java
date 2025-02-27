package src.com.webapp.exception;

public class StorageException extends RuntimeException {
    final private String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String uuid) {
        this.uuid = uuid;
    }
}
