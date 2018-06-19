package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genre")
public class GenreEntity extends AbstractDictionaryEntity {

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
    @Column(name = "code", nullable = false)
    public String getCode() {
        return super.getCode();
    }

    public void setCode(String code) {
        super.setCode(code);
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }


    @ManyToMany(mappedBy = "genres")
    public Collection<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<AlbumEntity> albums) {
        this.albums = albums;
    }
}
