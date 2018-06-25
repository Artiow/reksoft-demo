package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDictionaryDTO;

import javax.validation.constraints.*;

public class GenreDTO extends AbstractDictionaryDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String code;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public GenreDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public GenreDTO setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GenreDTO setName(String name) {
        this.name = name;
        return this;
    }


    public interface IdCheck {

    }

    public interface FieldCheck {

    }
}
