package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaTypeEntity;

public class MediaTypeDTO {

    private Integer id;
    private String code;
    private String name;
    private String description;


    public MediaTypeDTO(MediaTypeEntity entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }


    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
