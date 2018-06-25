package ru.reksoft.demo.dto.handling;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ValidateErrorDTO extends ErrorDTO {

    private final List<String> errors;

    public ValidateErrorDTO(UUID uuid, String exception, String objectName, List<ObjectError> allErrors) {
        super(uuid, exception, "Validation failed for " + objectName);
        this.errors = new ArrayList<>(allErrors.size());
        for (ObjectError error: allErrors) {
            this.errors.add(
                    ((DefaultMessageSourceResolvable) error.getArguments()[0]).getCodes()[0] + " " + error.getDefaultMessage()
            );
        }
    }


    public List<String> getErrors() {
        return errors;
    }
}
