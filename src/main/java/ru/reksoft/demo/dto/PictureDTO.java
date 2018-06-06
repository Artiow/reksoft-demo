package ru.reksoft.demo.dto;

import java.time.LocalDateTime;

public class PictureDTO {

    private Integer id;
    private String url;
    private String name;
    private Integer width;
    private Integer height;
    private LocalDateTime uploaded;


    public Integer getId() {
        return id;
    }

    public PictureDTO setId(Integer id) {
        this.id = id;
        return this;
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
