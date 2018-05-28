package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.GenreEntity;

import java.util.Comparator;

public class GenreDTO implements Comparable<GenreDTO> {

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


    @Override
    public int compareTo(GenreDTO o) {
        return name.compareTo(o.name);
    }

    public static class ComparatorByName implements Comparator<GenreDTO> {
        @Override
        public int compare(GenreDTO o1, GenreDTO o2) { return o1.compareTo(o2); }
    }
}
