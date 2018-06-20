package ru.reksoft.demo.util;

import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BindingResultErrorMessageBuilder {

    public static String build(@NotNull String objectName, @NotNull List<ObjectError> errors) {
        StringBuilder builder = new StringBuilder(objectName + " validate failed!");

        if (errors.size() > 1) {
            builder.append(" error number: ").append(errors.size());
        } else {
            builder.append(' ').append(errors.get(0).getDefaultMessage());
        }

        return builder.toString();
    }
}
