package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.SingerEntity;

public class SingerDTO {

    private Integer id;
    private String name;


    public SingerDTO(SingerEntity entity) {
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
