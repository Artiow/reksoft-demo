package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.UserRoleEntity;

public class UserRoleDTO {

    private Integer id;
    private String name;
    private String code;
    private String description;


    public UserRoleDTO(UserRoleEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.description = entity.getDescription();
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
