package ru.reksoft.demo.dto.shortcut;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.net.URI;

public class AlbumShortDTO extends AbstractIdentifiedDTO {

    private Integer id;
    private String name;
    private String label;
    private String singer;
    private URI pictureURI;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    public URI getPictureURI() {
        return pictureURI;
    }

    public AlbumShortDTO setPictureURI(URI pictureURI) {
        this.pictureURI = pictureURI;
        return this;
    }
}
