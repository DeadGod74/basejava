package src.com.webapp.model;

public class Resume {
    private String uuid;


    public String getUuid() {
        return uuid;
    }

    public void SetUuid (String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}