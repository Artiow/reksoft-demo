package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "album")
public class AlbumEntity extends AbstractIdentifiedEntity {

    private String name;
    private String description;
    private Timestamp releaseYear;

    private LabelEntity label;
    private SingerEntity singer;
    private PictureEntity picture;

    private Collection<MediaEntity> media;
    private Collection<GenreEntity> genres;
    private Collection<CompositionEntity> compositions;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return super.getId();
    }

    public void setId(Integer id) {
        super.setId(id);
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "release_year", nullable = false)
    public Timestamp getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Timestamp releaseYear) {
        this.releaseYear = releaseYear;
    }


    @OneToOne
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }


    @OneToMany(mappedBy = "album")
    public Collection<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaEntity> media) {
        this.media = media;
    }

    @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST)
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
        this.compositions = compositions;
    }


    @ManyToOne
    @JoinColumn(name = "label_id", referencedColumnName = "id", nullable = false)
    public LabelEntity getLabel() {
        return label;
    }

    public void setLabel(LabelEntity label) {
        this.label = label;
    }

    @ManyToOne
    @JoinColumn(name = "singer_id", referencedColumnName = "id", nullable = false)
    public SingerEntity getSinger() {
        return singer;
    }

    public void setSinger(SingerEntity singer) {
        this.singer = singer;
    }


    @ManyToMany
    @JoinTable(name = "album_genres", catalog = "reksoft", schema = "demo", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false))
    public Collection<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenreEntity> genres) {
        this.genres = genres;
    }
}
