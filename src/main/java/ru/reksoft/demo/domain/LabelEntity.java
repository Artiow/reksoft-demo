package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "label")
public class LabelEntity extends AbstractIdentifiedEntity {

    private String name;

    private Collection<AlbumEntity> albums;


    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "label")
    public Collection<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<AlbumEntity> albums) {
        this.albums = albums;
    }
}
