package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "singer")
public class SingerEntity extends AbstractIdentifiedEntity {

    private String name;

    private Collection<AlbumEntity> albums;

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


    @OneToMany(mappedBy = "singer")
    public Collection<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<AlbumEntity> albums) {
        this.albums = albums;
    }
}
