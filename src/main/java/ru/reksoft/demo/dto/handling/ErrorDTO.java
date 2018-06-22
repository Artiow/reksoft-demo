package ru.reksoft.demo.dto.handling;

public class ErrorDTO {

    private final String exception;
    private final String message;

    public ErrorDTO(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
