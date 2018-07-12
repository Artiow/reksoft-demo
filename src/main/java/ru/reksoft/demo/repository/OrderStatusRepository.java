package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.OrderStatusEntity;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer>, JpaSpecificationExecutor<OrderStatusEntity> {

}
