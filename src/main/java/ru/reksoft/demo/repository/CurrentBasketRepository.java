package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reksoft.demo.domain.CurrentBasketEntity;
import ru.reksoft.demo.domain.CurrentBasketEntityPK;

import java.util.List;

public interface CurrentBasketRepository extends JpaRepository<CurrentBasketEntity, CurrentBasketEntityPK>, JpaSpecificationExecutor<CurrentBasketEntity> {

    @Query("from CurrentBasketEntity where pk.userId=:userId group by pk.userId, pk.mediaId")
    List<CurrentBasketEntity> findByUserId(@Param("userId") Integer userId);
}
