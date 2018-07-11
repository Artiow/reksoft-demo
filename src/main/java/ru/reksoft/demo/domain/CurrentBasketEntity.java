package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.DomainObject;

import javax.persistence.*;

@Entity
@Table(name = "current_basket")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.media", joinColumns = @JoinColumn(name = "media_id"))
})
public class CurrentBasketEntity implements DomainObject {

    @EmbeddedId
    private CurrentBasketEntityPK pk;

    @Basic
    @Column(name = "media_count", nullable = false)
    private Integer count = 1;


    public CurrentBasketEntity() {
        pk = new CurrentBasketEntityPK();
    }


    public CurrentBasketEntityPK getPK() {
        return pk;
    }

    public void setPK(CurrentBasketEntityPK pk) {
        this.pk = pk;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Transient
    public UserEntity getUser() {
        return pk.getUser();
    }

    public void setUser(UserEntity user) {
        pk.setUser(user);
    }

    @Transient
    public MediaEntity getMedia() {
        return pk.getMedia();
    }

    public void setMedia(MediaEntity media) {
        pk.setMedia(media);
    }
}
