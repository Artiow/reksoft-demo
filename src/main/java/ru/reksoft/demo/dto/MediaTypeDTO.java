package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDescriptiveDictionaryDTO;

import javax.validation.constraints.*;

public class MediaTypeDTO extends AbstractDescriptiveDictionaryDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = CreateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
    private String code;

    @NotNull(groups = CreateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
    private String name;

    @Size(min = 1, max = 90, groups = UpdateCheck.class)
    private String description;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public MediaTypeDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public MediaTypeDTO setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public MediaTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public MediaTypeDTO setDescription(String description) {
        this.description = description;
        return this;
    }


    public interface IdCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
