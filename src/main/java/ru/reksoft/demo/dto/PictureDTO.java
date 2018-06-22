package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PictureDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    private String url;
    private String name;
    private Integer width;
    private Integer height;
    private LocalDateTime uploaded;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
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


    public interface IdCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
