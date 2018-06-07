package ru.reksoft.demo.dto;

public class UserRoleDTO {

    private Integer id;
    private String code;
    private String name;
    private String description;


    public Integer getId() {
        return id;
    }

    public UserRoleDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UserRoleDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRoleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserRoleDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
