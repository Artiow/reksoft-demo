package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.SingerEntity;

public interface SingerRepository extends JpaRepository<SingerEntity, Integer>, JpaSpecificationExecutor<SingerEntity> {

    boolean existsByName(String name);
}
