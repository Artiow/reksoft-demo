package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDictionaryDTO;

import javax.validation.constraints.*;

public class GenreDTO extends AbstractDictionaryDTO {

    @NotNull(groups = IdentifierCheck.class)
    @Min(value = 1, groups = IdentifierCheck.class)
    private Integer id;

    @NotNull(groups = CreateCheck.class)
    @NotEmpty(groups = UpdateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
    private String code;

    @NotNull(groups = CreateCheck.class)
    @NotEmpty(groups = UpdateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
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


    public interface IdentifierCheck extends UpdateCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
