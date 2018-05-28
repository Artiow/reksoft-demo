package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.domain.GenreEntity;

import java.util.*;

public class AlbumDTO {

    private Integer id;
    private String name;
    private Date release;

    private LabelDTO label;
    private SingerDTO singer;
    private PictureDTO picture;

    private List<GenreDTO> genres;
    private List<CompositionDTO> compositions;


    public AlbumDTO(AlbumEntity entity) {
        id = entity.getId();
        name = entity.getName();
        release = new Date(entity.getRelease().getTime());

        label = new LabelDTO(entity.getLabel());
        singer = new SingerDTO(entity.getSinger());

        try { picture = new PictureDTO(entity.getPicture()); } catch (NullPointerException e) { picture = null; }

        Collection<GenreEntity> genreEntities = entity.getGenres();
        genres = new ArrayList<>(genreEntities.size());
        for(GenreEntity e: genreEntities) genres.add(new GenreDTO(e));
        Collections.sort(genres);

        Collection<CompositionEntity> compositionEntities = entity.getCompositions();
        compositions = new ArrayList<>(compositionEntities.size());
        for(CompositionEntity e: compositionEntities) compositions.add(new CompositionDTO(e));
        Collections.sort(compositions);
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getRelease() {
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