package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "media_type")
public class MediaTypeEntity extends AbstractDescriptiveDictionaryEntity {

    private Collection<MediaEntity> media;


    @OneToMany(mappedBy = "type")
    public Collection<MediaEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaEntity> media) {
        this.media = media;
    }
}
