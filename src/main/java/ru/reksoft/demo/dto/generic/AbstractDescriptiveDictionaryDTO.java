package ru.reksoft.demo.dto.generic;

import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public abstract class AbstractDescriptiveDictionaryDTO extends AbstractDictionaryDTO {

    @NotEmpty(message = "description must not be empty!", groups = CreateCheck.class)
    @Max(value = 90, message = "description must not be longer than 45 characters!", groups = CreateCheck.class)
    private String description;


    public String getDescription() {
        return description;
    }

    public AbstractDescriptiveDictionaryDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
