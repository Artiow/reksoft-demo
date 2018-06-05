package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.mapper.LabelMapper;
import ru.reksoft.demo.mapper.PictureMapper;
import ru.reksoft.demo.mapper.SingerMapper;

import java.time.LocalDate;
import java.util.*;

public class AlbumDTO {

    private Integer id;
    private String name;
    private String description;
    private LocalDate releaseYear;

    private LabelDTO label;
    private SingerDTO singer;
    private PictureDTO picture;

    private List<GenreDTO> genres;
    private List<CompositionDTO> compositions;


    public AlbumDTO(AlbumEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.releaseYear = entity.getReleaseYear().toLocalDateTime().toLocalDate();

        this.label = LabelMapper.INSTANCE.toDTO(entity.getLabel());
        this.singer = SingerMapper.INSTANCE.toDTO(entity.getSinger());
        this.picture = PictureMapper.INSTANCE.toDTO(entity.getPicture());

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

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
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
