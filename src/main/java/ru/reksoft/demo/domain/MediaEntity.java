package ru.reksoft.demo.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "media", schema = "demo", catalog = "reksoft")
public class MediaEntity {

    private Integer id;
    private Integer price;

    private AlbumEntity album;
    private MediaTypeEntity type;

    private Collection<CurrentBasketEntity> baskets;
    private Collection<MediaOrderEntity> orders;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaEntity that = (MediaEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id", nullable = false)
    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public MediaTypeEntity getType() {
        return type;
    }

    public void setType(MediaTypeEntity type) {
        this.type = type;
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
}
