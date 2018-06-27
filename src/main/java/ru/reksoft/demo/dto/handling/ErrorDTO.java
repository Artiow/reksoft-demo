package ru.reksoft.demo.dto.handling;

import java.util.UUID;

public class ErrorDTO {

    private final UUID uuid;
    private final String exception;
    private final String message;


    public ErrorDTO(UUID uuid, String exception, String message) {
        this.uuid = uuid;
        this.exception = exception;
        this.message = message;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
