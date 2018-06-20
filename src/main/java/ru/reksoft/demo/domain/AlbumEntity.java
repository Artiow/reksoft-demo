package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "album")
public class AlbumEntity extends AbstractIdentifiedEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "release_year", nullable = false)
    private Timestamp releaseYear;

    @OneToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    private PictureEntity picture;

    @OneToMany(mappedBy = "album")
    private Collection<MediaEntity> media;

    @OrderBy("position")
    @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST)
    private Collection<CompositionEntity> compositions;

    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id", nullable = false)
    private LabelEntity label;

    @ManyToOne
    @JoinColumn(name = "singer_id", referencedColumnName = "id", nullable = false)
    private SingerEntity singer;

    @ManyToMany
    @JoinTable(
            name = "album_genres",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    )
    private Collection<GenreEntity> genres;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Timestamp releaseYear) {
        this.releaseYear = releaseYear;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }

    public Collection<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaEntity> media) {
        this.media = media;
    }

    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
        this.compositions = compositions;
    }

    public LabelEntity getLabel() {
        return label;
    }

    public void setLabel(LabelEntity label) {
        this.label = label;
    }

    public SingerEntity getSinger() {
        return singer;
    }

    public void setSinger(SingerEntity singer) {
        this.singer = singer;
    }

    public Collection<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenreEntity> genres) {
        this.genres = genres;
    }
}
