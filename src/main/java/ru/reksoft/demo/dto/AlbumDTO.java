package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

public class AlbumDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
    private String name;

    @NotEmpty(message = "description must not be empty!", groups = CreateCheck.class)
    @Max(value = 255, message = "description must not be longer than 255 characters!", groups = CreateCheck.class)
    private String description;

    @NotNull(message = "releaseYear must not be null!", groups = CreateCheck.class)
    private LocalDate releaseYear;

    @NotNull(message = "label must not be null!", groups = CreateCheck.class)
    private LabelDTO label;

    @NotNull(message = "singer must not be null!", groups = CreateCheck.class)
    private SingerDTO singer;

    private PictureDTO picture;

    @NotNull(message = "genre list must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "genre list must not be empty!", groups = CreateCheck.class)
    private List<GenreDTO> genres;

    @NotNull(message = "composition list not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "composition list must not be empty!", groups = CreateCheck.class)
    private List<CompositionDTO> compositions;


    public AlbumDTO setId(Integer id) {
        return (AlbumDTO) super.setId(id);
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

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public AlbumDTO setReleaseYear(LocalDate releaseYear) {
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
}
