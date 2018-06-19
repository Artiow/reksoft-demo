package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "media_type")
public class MediaTypeEntity extends AbstractDescriptiveDictionaryEntity {

    private Collection<MediaEntity> media;


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

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }


    @OneToMany(mappedBy = "type")
    public Collection<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaEntity> media) {
        this.media = media;
    }
}
