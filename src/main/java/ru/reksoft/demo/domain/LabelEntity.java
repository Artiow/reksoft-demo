package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "label")
public class LabelEntity extends AbstractVersionedEntity {

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "label")
    private Collection<AlbumEntity> albums;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<AlbumEntity> albums) {
        this.albums = albums;
    }
}
