package webapp.exeption;

import java.io.IOException;
import java.sql.SQLException;

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

    public StorageExeption(Exception e) {
        this (e.getMessage(),null);
    }

    public String getUuid() {
        return uuid;
    }
}

