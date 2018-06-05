package ru.reksoft.demo.dto;

public class MediaTypeDTO {

    private Integer id;
    private String code;
    private String name;
    private String description;


    public Integer getId() {
        return id;
    }

    public MediaTypeDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MediaTypeDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public MediaTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MediaTypeDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
