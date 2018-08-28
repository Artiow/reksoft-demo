package ru.reksoft.demo.dto.shortcut;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.net.URI;

public class MediaShortDTO extends AbstractIdentifiedDTO {

    private Integer id;
    private Integer price;
    private String type;
    private String album;
    private Integer albumId;
    private String label;
    private Integer labelId;
    private String singer;
    private Integer singerId;
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

    public Integer getAlbumId() {
        return albumId;
    }

    public MediaShortDTO setAlbumId(Integer albumId) {
        this.albumId = albumId;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public MediaShortDTO setLabel(String label) {
        this.label = label;
        return this;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public MediaShortDTO setLabelId(Integer labelId) {
        this.labelId = labelId;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public MediaShortDTO setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public MediaShortDTO setSingerId(Integer singerId) {
        this.singerId = singerId;
        return this;
    }

    public URI getPictureURI() {
        return pictureURI;
    }

    public void setPictureURI(URI pictureURI) {
        this.pictureURI = pictureURI;
    }
}
