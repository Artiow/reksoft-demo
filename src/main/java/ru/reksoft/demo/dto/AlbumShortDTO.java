package ru.reksoft.demo.dto;

public class AlbumShortDTO {

    private Integer id;
    private String name;
    private String label;
    private String singer;


    public Integer getId() {
        return id;
    }

    public AlbumShortDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumShortDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public AlbumShortDTO setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public AlbumShortDTO setSinger(String singer) {
        this.singer = singer;
        return this;
    }
}
