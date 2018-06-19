package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "media_type")
public class MediaTypeEntity extends AbstractDescriptiveDictionaryEntity {

    @OneToMany(mappedBy = "type")
    private Collection<MediaEntity> media;


    public Collection<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaEntity> media) {
        this.media = media;
    }
}
