package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaTypeEntity;

public class MediaTypeDTO {

    private Integer id;
    private String name;


    public MediaTypeDTO(MediaTypeEntity entity) {
        id = entity.getId();
        name = entity.getName();
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
