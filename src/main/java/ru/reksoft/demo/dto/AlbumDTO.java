package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.domain.PictureEntity;

import java.time.LocalDate;
import java.util.*;

public class AlbumDTO {

    private Integer id;
    private String name;
    private LocalDate release;

    private LabelDTO label;
    private SingerDTO singer;
    private PictureDTO picture;

    private List<GenreDTO> genres;
    private List<CompositionDTO> compositions;


    public AlbumDTO(AlbumEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.release = entity.getRelease().toLocalDateTime().toLocalDate();

        this.label = new LabelDTO(entity.getLabel());
        this.singer = new SingerDTO(entity.getSinger());

        PictureEntity pictureEntity = entity.getPicture();
        if (pictureEntity != null) {
            this.picture = new PictureDTO(pictureEntity);
        }

        Collection<GenreEntity> genreEntities = entity.getGenres();
        this.genres = new ArrayList<>(genreEntities.size());
        for(GenreEntity e: genreEntities) {
            this.genres.add(new GenreDTO(e));
        }

        Collections.sort(this.genres);

        Collection<CompositionEntity> compositionEntities = entity.getCompositions();
        this.compositions = new ArrayList<>(compositionEntities.size());
        for(CompositionEntity e: compositionEntities) {
            this.compositions.add(new CompositionDTO(e));
        }

        Collections.sort(this.compositions);
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getRelease() {
        return release;
    }

    public LabelDTO getLabel() {
        return label;
    }

    public SingerDTO getSinger() {
        return singer;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public Collection<GenreDTO> getGenres() {
        return genres;
    }

    public Collection<CompositionDTO> getCompositions() {
        return compositions;
    }
}
