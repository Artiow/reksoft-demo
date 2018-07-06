package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractVersionedDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MediaDTO extends AbstractVersionedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = VersionCheck.class)
    @Min(value = 1, groups = VersionCheck.class)
    private Long version;

    @NotNull(groups = FieldCheck.class)
    @Min(value = 0, groups = FieldCheck.class)
    private Integer price;

    @Valid
    @NotNull(groups = FieldCheck.class)
    private MediaTypeDTO type;

    @Valid
    @NotNull(groups = FieldCheck.class)
    private AlbumDTO album;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public MediaDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public MediaDTO setVersion(Long version) {
        this.version = version;
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


    public interface IdCheck extends VersionCheck {

    }

    public interface CreateCheck extends FieldCheck {

    }

    public interface UpdateCheck extends VersionCheck, FieldCheck {

    }


    private interface VersionCheck {

    }

    public interface FieldCheck extends
            MediaTypeDTO.IdCheck, AlbumDTO.IdCheck {

    }
}
