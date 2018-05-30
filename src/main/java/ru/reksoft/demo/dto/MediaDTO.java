package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaDTO {

    private Integer id;
    private Integer price;

    private MediaTypeDTO type;
    private AlbumDTO album;


    public MediaDTO(MediaEntity entity) {
        id = entity.getId();
        price = entity.getPrice();

        type = new MediaTypeDTO(entity.getType());
        album = new AlbumDTO(entity.getAlbum());
    }


    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public MediaTypeDTO getType() {
        return type;
    }

    public AlbumDTO getAlbum() {
        return album;
    }
}
