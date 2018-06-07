package ru.reksoft.demo.dto;

public class GenreDTO implements Comparable<GenreDTO> {

    private Integer id;
    private String code;
    private String name;


    public Integer getId() {
        return id;
    }

    public GenreDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public GenreDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public GenreDTO setName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public int compareTo(GenreDTO o) {
        return code.compareTo(o.code);
    }
}
