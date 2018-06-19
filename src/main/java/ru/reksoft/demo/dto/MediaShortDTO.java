package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class MediaShortDTO extends AbstractIdentifiedDTO {

    private Integer price;
    private String label;
    private String singer;
    private String album;
    private String type;

    private PictureDTO picture;


    public MediaShortDTO setId(Integer id) {
        return (MediaShortDTO) super.setId(id);
    }


    public Integer getPrice() {
        return price;
    }

    public MediaShortDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public MediaShortDTO setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public MediaShortDTO setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public String getAlbum() {
        return album;
    }

    public MediaShortDTO setAlbum(String album) {
        this.album = album;
        return this;
    }

    public String getType() {
        return type;
    }

    public MediaShortDTO setType(String type) {
        this.type = type;
        return this;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public MediaShortDTO setPicture(PictureDTO picture) {
        this.picture = picture;
        return this;
    }
}
