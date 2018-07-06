package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractDescriptiveDictionaryDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRoleDTO extends AbstractDescriptiveDictionaryDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String code;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;

    @Size(min = 1, max = 90, groups = FieldCheck.class)
    private String description;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public UserRoleDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public UserRoleDTO setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UserRoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public UserRoleDTO setDescription(String description) {
        this.description = description;
        return this;
    }


    public interface IdCheck {

    }

    public interface FieldCheck {

    }
}
