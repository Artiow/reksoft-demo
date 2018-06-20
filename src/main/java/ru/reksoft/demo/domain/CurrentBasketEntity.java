package ru.reksoft.demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "current_basket")
public class CurrentBasketEntity {

    @EmbeddedId
    private CurrentBasketEntityPK pk;

    private Integer count = 1;

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
