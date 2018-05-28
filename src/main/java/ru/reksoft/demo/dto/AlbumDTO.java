package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.domain.PictureEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class AlbumDTO {

    private Integer id;
    private String name;
    private Date release;

    private LabelDTO label;
    private SingerDTO singer;
    private PictureDTO picture;

    private Collection<GenreDTO> genres;
    private Collection<CompositionDTO> compositions;


    public AlbumDTO(AlbumEntity entity) {
        id = entity.getId();
        name = entity.getName();
        release = new Date(entity.getRelease().getTime());

        label = new LabelDTO(entity.getLabel());
        singer = new SingerDTO(entity.getSinger());

        PictureEntity pictureEntity = entity.getPicture();
        if (pictureEntity != null) picture = new PictureDTO(pictureEntity);

        Collection<GenreEntity> genreEntities = entity.getGenres();
        genres = new ArrayList<>(genreEntities.size());
        for(GenreEntity e: genreEntities) genres.add(new GenreDTO(e));

//        TODO: sort by position
        Collection<CompositionEntity> compositionEntities = entity.getCompositions();
        compositions = new ArrayList<>(compositionEntities.size());
        for(CompositionEntity e: compositionEntities) compositions.add(new CompositionDTO(e));
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
