package ru.reksoft.demo.dto;

public class MediaDTO {

    private Integer id;
    private Integer price;

    private MediaTypeDTO type;
    private AlbumDTO album;


    public Integer getId() {
        return id;
    }

    public MediaDTO setId(Integer id) {
        this.id = id;
        return this;
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
