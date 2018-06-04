package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.GenreEntity;

import java.util.Comparator;

public class GenreDTO implements Comparable<GenreDTO> {

    private Integer id;
    private String code;
    private String name;


    public GenreDTO(GenreEntity entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
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


    @Override
    public int compareTo(GenreDTO o) {
        return name.compareTo(o.name);
    }
}
