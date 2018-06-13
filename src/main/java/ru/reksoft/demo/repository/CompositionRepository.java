package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.CompositionEntity;

public interface CompositionRepository extends JpaRepository<CompositionEntity, Integer>, JpaSpecificationExecutor<CompositionEntity> {

}
