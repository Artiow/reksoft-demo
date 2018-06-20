package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SingerDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
    private String name;


    public SingerDTO setId(Integer id) {
        return (SingerDTO) super.setId(id);
    }


    public String getName() {
        return name;
    }

    public SingerDTO setName(String name) {
        this.name = name;
        return this;
    }
}
