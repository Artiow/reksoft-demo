package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.PictureEntity;

import java.time.LocalDateTime;

public class PictureDTO {

    private Integer id;
    private String url;
    private String name;
    private Integer width;
    private Integer height;
    private LocalDateTime uploaded;


    public PictureDTO(PictureEntity entity) {
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.name = entity.getName();
        this.width = entity.getWidth();
        this.height = entity.getHeight();

        this.uploaded = entity.getUploaded().toLocalDateTime();
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

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public LocalDateTime getUploaded() {
        return uploaded;
    }
}
