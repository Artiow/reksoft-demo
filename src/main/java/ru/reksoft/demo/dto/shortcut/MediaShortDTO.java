package ru.reksoft.demo.dto.shortcut;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.net.URI;

public class MediaShortDTO extends AbstractIdentifiedDTO {

    private Integer id;
    private Integer price;
    private String type;
    private String album;
    private String label;
    private String singer;
    private URI pictureURI;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public MediaShortDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public URI getPictureURI() {
        return pictureURI;
    }

    public void setPictureURI(URI pictureURI) {
        this.pictureURI = pictureURI;
    }
}
