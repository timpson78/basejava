package webapp.exeption;

import java.io.IOException;

public class StorageExeption extends RuntimeException {
    private final String uuid;

    public StorageExeption(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageExeption(String message, String uuid, Exception e) {
        super(message,e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}

