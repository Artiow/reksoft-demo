package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.LabelEntity;

public class LabelDTO {

    private Integer id;
    private String name;


    public LabelDTO(LabelEntity entity) {
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
