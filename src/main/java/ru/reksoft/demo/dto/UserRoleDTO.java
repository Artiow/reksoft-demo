package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.UserRoleEntity;

public class UserRoleDTO {

    private Integer id;
    private String name;
    private String code;
    private String description;

    public UserRoleDTO() {

    }

    public UserRoleDTO(UserRoleEntity entity) {
        id = entity.getId();
        name = entity.getName();
        code = entity.getCode();
        description = entity.getDescription();
    }


    public Integer getId() {
        return id;
    }

    public UserRoleDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UserRoleDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserRoleDTO setDescription(String description) {
        this.description = description;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleDTO that = (UserRoleDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
