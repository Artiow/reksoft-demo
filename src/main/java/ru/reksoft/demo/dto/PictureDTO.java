package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.PictureEntity;

import java.util.Date;

public class PictureDTO {

    private Integer id;
    private String url;
    private String name;
    private Integer size;
    private Date uploaded;


    public PictureDTO(PictureEntity entity) {
        id = entity.getId();
        url = entity.getUrl();
        name = entity.getName();
        size = entity.getSize();

        uploaded = new Date(entity.getUploaded().getTime());
    }


    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }

    public Date getUploaded() {
        return uploaded;
    }
}
