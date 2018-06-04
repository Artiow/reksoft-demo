package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.MediaEntity;

public class MediaDTO {

    private Integer id;
    private Integer price;

    private MediaTypeDTO type;
    private AlbumDTO album;


    public MediaDTO(MediaEntity entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();

        this.type = new MediaTypeDTO(entity.getType());
        this.album = new AlbumDTO(entity.getAlbum());
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
