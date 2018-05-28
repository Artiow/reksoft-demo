package ru.reksoft.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "current_basket", schema = "demo", catalog = "reksoft")
public class CurrentBasketEntity {

    @EmbeddedId
    private CurrentBasketEntityPK pk;

    private Integer count;

    @ManyToOne
    @MapsId("userId")
    private UserEntity user;
    @ManyToOne
    @MapsId("mediaId")
    private MediaEntity media;


    public CurrentBasketEntity() {
        pk = new CurrentBasketEntityPK();
    }


    public CurrentBasketEntityPK getPK() {
        return pk;
    }

    public void setPK(CurrentBasketEntityPK pk) {
        this.pk = pk;
    }

    @Basic
    @Column(name = "media_count", nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentBasketEntity that = (CurrentBasketEntity) o;

        return pk.equals(that.pk);
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id", nullable = false)
    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }
}
