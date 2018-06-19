package ru.reksoft.demo.domain;

import ru.reksoft.demo.domain.generic.AbstractDescriptiveDictionaryEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity extends AbstractDescriptiveDictionaryEntity {

    @OneToMany(mappedBy = "status")
    private Collection<OrderEntity> orders;


    public Collection<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderEntity> orders) {
        this.orders = orders;
    }
}
