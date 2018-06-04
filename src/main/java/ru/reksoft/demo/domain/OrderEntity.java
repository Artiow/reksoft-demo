package ru.reksoft.demo.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order")
public class OrderEntity {

    private Integer id;
    private String address;
    private Timestamp ordered;

    private OrderStatusEntity status;

    private Collection<MediaOrderEntity> media;


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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "ordered")
    public Timestamp getOrdered() {
        return ordered;
    }

    public void setOrdered(Timestamp ordered) {
        this.ordered = ordered;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public OrderStatusEntity getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEntity status) {
        this.status = status;
    }


    @OneToMany(mappedBy = "order")
    public Collection<MediaOrderEntity> getMedia() {
        return media;
    }

    public void setMedia(Collection<MediaOrderEntity> media) {
        this.media = media;
    }
}
