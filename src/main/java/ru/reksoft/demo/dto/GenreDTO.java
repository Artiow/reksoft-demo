package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.GenreEntity;

import java.util.Comparator;

public class GenreDTO implements Comparable<GenreDTO> {

    private Integer id;
    private String name;
    private String code;


    public GenreDTO(GenreEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
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


    @Override
    public int compareTo(GenreDTO o) {
        return name.compareTo(o.name);
    }
}
