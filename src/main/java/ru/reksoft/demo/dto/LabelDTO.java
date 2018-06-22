package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.*;

public class LabelDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdentifierCheck.class)
    @Min(value = 1, groups = IdentifierCheck.class)
    private Integer id;

    @NotNull(groups = CreateCheck.class)
    @NotEmpty(groups = UpdateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
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


    public interface IdentifierCheck extends UpdateCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
