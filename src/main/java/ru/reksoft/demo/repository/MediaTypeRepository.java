package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.MediaTypeEntity;

public interface MediaTypeRepository extends JpaRepository<MediaTypeEntity, Integer>, JpaSpecificationExecutor<MediaTypeEntity> {

}
