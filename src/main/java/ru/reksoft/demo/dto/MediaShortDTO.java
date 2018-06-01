package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.domain.PictureEntity;

public class MediaShortDTO {

    private Integer id;
    private Integer price;
    private String singer;
    private String album;
    private String type;

    private PictureDTO picture;


    public MediaShortDTO(MediaEntity entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.type = entity.getType().getName();

        AlbumEntity albumEntity = entity.getAlbum();
        this.singer = albumEntity.getSinger().getName();
        this.album = albumEntity.getName();

        PictureEntity pictureEntity = albumEntity.getPicture();
        if (pictureEntity != null) {
            this.picture = new PictureDTO(pictureEntity);
        }
    }


    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getSinger() {
        return singer;
    }

    public String getAlbum() {
        return album;
    }

    public String getType() {
        return type;
    }

    public PictureDTO getPicture() {
        return picture;
    }
}
