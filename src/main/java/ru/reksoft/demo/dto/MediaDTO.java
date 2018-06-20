package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

public class MediaDTO extends AbstractIdentifiedDTO {

    private Integer price;

    private MediaTypeDTO type;
    private AlbumDTO album;


    public MediaDTO setId(Integer id) {
        return (MediaDTO) super.setId(id);
    }


    public Integer getPrice() {
        return price;
    }

    public MediaDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public MediaTypeDTO getType() {
        return type;
    }

    public MediaDTO setType(MediaTypeDTO type) {
        this.type = type;
        return this;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public MediaDTO setAlbum(AlbumDTO album) {
        this.album = album;
        return this;
    }
}
