package ru.reksoft.demo.dto;

public class SingerDTO {

    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public SingerDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SingerDTO setName(String name) {
        this.name = name;
        return this;
    }
}
