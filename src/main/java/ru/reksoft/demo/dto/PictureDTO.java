package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.time.LocalDateTime;

public class PictureDTO extends AbstractIdentifiedDTO {

    private String url;
    private String name;
    private Integer width;
    private Integer height;
    private LocalDateTime uploaded;


    public PictureDTO setId(Integer id) {
        return (PictureDTO) super.setId(id);
    }


    public String getUrl() {
        return url;
    }

    public PictureDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public PictureDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public PictureDTO setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public PictureDTO setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public LocalDateTime getUploaded() {
        return uploaded;
    }

    public PictureDTO setUploaded(LocalDateTime uploaded) {
        this.uploaded = uploaded;
        return this;
    }
}
