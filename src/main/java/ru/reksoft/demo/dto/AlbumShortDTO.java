package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class AlbumShortDTO extends AbstractIdentifiedDTO {

    private String name;
    private String label;
    private String singer;


    public AlbumShortDTO setId(Integer id) {
        return (AlbumShortDTO) super.setId(id);
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
