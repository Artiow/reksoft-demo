package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractIdentifiedEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "media")
public class MediaEntity extends AbstractIdentifiedEntity {

    private Integer price;

    private MediaTypeEntity type;
    private AlbumEntity album;

    private Collection<CurrentBasketEntity> baskets;
    private Collection<MediaOrderEntity> orders;


    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @OneToMany(mappedBy = "media")
    public Collection<CurrentBasketEntity> getBaskets() {
        return baskets;
    }

    public void setBaskets(Collection<CurrentBasketEntity> baskets) {
        this.baskets = baskets;
    }

    @OneToMany(mappedBy = "media")
    public Collection<MediaOrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<MediaOrderEntity> orders) {
        this.orders = orders;
    }


    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public MediaTypeEntity getType() {
        return type;
    }

    public void setType(MediaTypeEntity type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}
