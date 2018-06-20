package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PictureDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "url must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "url must not be empty!", groups = CreateCheck.class)
    @Max(value = 255, message = "url must not be longer than 255 characters!", groups = CreateCheck.class)
    private String url;

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
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
