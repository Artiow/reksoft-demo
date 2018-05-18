package ru.reksoft.demo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "album", schema = "demo", catalog = "reksoft")
public class AlbumEntity {

    private Integer id;
    private String name;
    private String label;
    private String singer;
    private Date release;

    private PictureEntity picture;

    private Collection<MediaEntity> media;
    private Collection<GenreEntity> genres;
    private Collection<CompositionEntity> compositions;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "label", nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "singer", nullable = false)
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Basic
    @Column(name = "release", nullable = false)
    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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

    @ManyToMany
    @JoinTable(name = "album_genres", catalog = "reksoft", schema = "demo", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false))
    public Collection<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Collection<GenreEntity> genres) {
        this.genres = genres;
    }

    @OneToMany(mappedBy = "album")
    public Collection<CompositionEntity> getCompositions() {
        return compositions;
    }

    public void setCompositions(Collection<CompositionEntity> compositions) {
        this.compositions = compositions;
    }
}
