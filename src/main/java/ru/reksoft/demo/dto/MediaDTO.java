package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MediaDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "id must not be null!", groups = CreateCheck.class)
    @Min(value = 0, message = "id must not less then zero!", groups = CreateCheck.class)
    private Integer price;

    @Valid
    @NotNull(message = "media type must not be null!", groups = CreateCheck.class)
    private MediaTypeDTO type;

    @Valid
    @NotNull(message = "album must not be null!", groups = CreateCheck.class)
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
