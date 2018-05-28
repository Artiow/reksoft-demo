package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.GenreEntity;

public class GenreDTO {

    private Integer id;
    private String name;
    private String code;


    public GenreDTO(GenreEntity entity) {
        id = entity.getId();
        name = entity.getName();
        code = entity.getCode();
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
}
