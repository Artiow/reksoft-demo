package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "composition")
public class CompositionEntity extends AbstractIdentifiedEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "album_pos", nullable = false)
    private Integer position;

    @Basic
    @Column(name = "duration", nullable = false)
    private Timestamp duration;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    private AlbumEntity album;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}
