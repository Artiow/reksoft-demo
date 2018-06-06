package ru.reksoft.demo.dto;

public class LabelDTO {

    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public LabelDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LabelDTO setName(String name) {
        this.name = name;
        return this;
    }
}
