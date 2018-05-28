package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaDTO {

    private Integer id;
    private Integer price;
    private String type;

    private AlbumDTO album;


    public MediaDTO(MediaEntity entity) {
        id = entity.getId();
        price = entity.getPrice();
        type = entity.getType().getName();

        album = new AlbumDTO(entity.getAlbum());
    }


    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public AlbumDTO getAlbum() {
        return album;
    }
}
