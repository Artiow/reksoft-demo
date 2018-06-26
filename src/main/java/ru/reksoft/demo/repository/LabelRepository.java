package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.LabelEntity;

public interface LabelRepository extends JpaRepository<LabelEntity, Integer>, JpaSpecificationExecutor<LabelEntity> {

    boolean existsByName(String name);
}
