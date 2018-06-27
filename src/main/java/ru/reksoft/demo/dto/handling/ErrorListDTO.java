package ru.reksoft.demo.dto.handling;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ErrorListDTO extends ErrorDTO {

    private final List<String> errors;


    public ErrorListDTO(UUID uuid, String exception, String message, List<String> errors) {
        super(uuid, exception, message);
        this.errors = new ArrayList<>(errors);
    }


    public List<String> getErrors() {
        return errors;
    }
}
