package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.time.LocalDate;
import java.util.*;

public class AlbumDTO extends AbstractIdentifiedDTO {

    private String name;
    private String description;
    private LocalDate releaseYear;

    private LabelDTO label;
    private SingerDTO singer;
    private PictureDTO picture;

    private PriorityQueue<GenreDTO> genres;
    private PriorityQueue<CompositionDTO> compositions;


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

    public PriorityQueue<GenreDTO> getGenres() {
        return genres;
    }

    public AlbumDTO setGenres(PriorityQueue<GenreDTO> genres) {
        this.genres = genres;
        return this;
    }

    public PriorityQueue<CompositionDTO> getCompositions() {
        return compositions;
    }

    public AlbumDTO setCompositions(PriorityQueue<CompositionDTO> compositions) {
        this.compositions = compositions;
        return this;
    }
}
