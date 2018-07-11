package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.CurrentBasketEntity;
import ru.reksoft.demo.domain.CurrentBasketEntityPK;

public interface CurrentBasketRepository extends JpaRepository<CurrentBasketEntity, CurrentBasketEntityPK>, JpaSpecificationExecutor<CurrentBasketEntity> {

    boolean existsByUserIdAndMediaId(Integer userId, Integer mediaId);
}
