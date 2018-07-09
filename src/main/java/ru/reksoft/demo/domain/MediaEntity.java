package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "media")
public class MediaEntity extends AbstractVersionedEntity {

    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "media")
    private Collection<CurrentBasketEntity> baskets;

    @OneToMany(mappedBy = "media")
    private Collection<MediaOrderEntity> orders;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private MediaTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    private AlbumEntity album;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Collection<CurrentBasketEntity> getBaskets() {
        return baskets;
    }

    public void setBaskets(Collection<CurrentBasketEntity> baskets) {
        this.baskets = baskets;
    }

    public Collection<MediaOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<MediaOrderEntity> orders) {
        this.orders = orders;
    }

    public MediaTypeEntity getType() {
        return type;
    }

    public void setType(MediaTypeEntity type) {
        this.type = type;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}
