package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.mapper.PictureMapper;

public class MediaShortDTO {

    private Integer id;
    private Integer price;
    private String label;
    private String singer;
    private String album;
    private String type;

    private PictureDTO picture;


    public MediaShortDTO(MediaEntity entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.type = entity.getType().getName();

        AlbumEntity albumEntity = entity.getAlbum();
        this.label = albumEntity.getLabel().getName();
        this.singer = albumEntity.getSinger().getName();
        this.album = albumEntity.getName();

        this.picture = PictureMapper.INSTANCE.toDTO(albumEntity.getPicture());
    }


    public Integer getId() {
        return id;
    }

    public MediaShortDTO setId(Integer id) {
        this.id = id;
        return this;
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
