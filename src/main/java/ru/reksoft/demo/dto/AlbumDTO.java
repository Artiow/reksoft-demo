package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractVersionedDTO;
import ru.reksoft.demo.dto.validation.annotations.PositionSequence;
import ru.reksoft.demo.dto.validation.annotations.ReleaseYear;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AlbumDTO extends AbstractVersionedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = VersionCheck.class)
    @Min(value = 1, groups = VersionCheck.class)
    private Long version;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;

    @Size(min = 1, max = 255, groups = FieldCheck.class)
    private String description;

    @NotNull(groups = FieldCheck.class)
    @ReleaseYear(groups = FieldCheck.class)
    private Integer releaseYear;

    @Valid
    @NotNull(groups = FieldCheck.class)
    private LabelDTO label;

    @Valid
    @NotNull(groups = FieldCheck.class)
    private SingerDTO singer;

    @Valid
    private PictureDTO picture;

    @Valid
    @NotNull(groups = FieldCheck.class)
    @NotEmpty(groups = FieldCheck.class)
    private List<GenreDTO> genres;

    @Valid
    @NotNull(groups = FieldCheck.class)
    @NotEmpty(groups = FieldCheck.class)
    @PositionSequence(groups = FieldCheck.class)
    private List<CompositionDTO> compositions;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public AlbumDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public AlbumDTO setVersion(Long version) {
        this.version = version;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public AlbumDTO setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public LabelDTO getLabel() {
        return label;
    }

    public AlbumDTO setLabel(LabelDTO label) {
        this.label = label;
        return this;
    }

    public SingerDTO getSinger() {
        return singer;
    }

    public AlbumDTO setSinger(SingerDTO singer) {
        this.singer = singer;
        return this;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public AlbumDTO setPicture(PictureDTO picture) {
        this.picture = picture;
        return this;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public AlbumDTO setGenres(List<GenreDTO> genres) {
        this.genres = genres;
        return this;
    }

    public List<CompositionDTO> getCompositions() {
        return compositions;
    }

    public AlbumDTO setCompositions(List<CompositionDTO> compositions) {
        this.compositions = compositions;
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

    private interface FieldCheck extends
            LabelDTO.IdCheck, SingerDTO.IdCheck, PictureDTO.IdCheck, GenreDTO.IdCheck, CompositionDTO.FieldCheck {

    }
}
