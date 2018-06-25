package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.*;

public class LabelDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public LabelDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LabelDTO setName(String name) {
        this.name = name;
        return this;
    }


    public interface IdCheck {

    }

    public interface FieldCheck {

    }
}
