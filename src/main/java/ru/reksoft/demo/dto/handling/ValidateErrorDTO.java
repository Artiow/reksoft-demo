package ru.reksoft.demo.dto.handling;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidateErrorDTO extends ErrorDTO {

    private final List<String> errors;

    public ValidateErrorDTO(String exception, String message, List<ObjectError> errors) {
        super(exception, message);
        this.errors = new ArrayList<>(errors.size());
        for (ObjectError error: errors) {
            this.errors.add(
                    ((DefaultMessageSourceResolvable) error.getArguments()[0]).getCodes()[0] + " " + error.getDefaultMessage()
            );
        }
    }

    public List<String> getErrors() {
        return errors;
    }
}
