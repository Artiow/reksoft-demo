package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LabelDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
    private String name;


    public LabelDTO setId(Integer id) {
        return (LabelDTO) super.setId(id);
    }


    public String getName() {
        return name;
    }

    public LabelDTO setName(String name) {
        this.name = name;
        return this;
    }
}
