package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genre")
public class GenreEntity extends AbstractDictionaryEntity {

    @ManyToMany(mappedBy = "genres")
    private Collection<AlbumEntity> albums;


    public Collection<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<AlbumEntity> albums) {
        this.albums = albums;
    }
}
