package ru.reksoft.demo.dto.generic;

import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class AbstractDictionaryDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "code must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "code must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "code must not be longer than 45 characters!", groups = CreateCheck.class)
    private String code;

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
    private String name;


    public String getCode() {
        return code;
    }

    public AbstractDictionaryDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public AbstractDictionaryDTO setName(String name) {
        this.name = name;
        return this;
    }
}
