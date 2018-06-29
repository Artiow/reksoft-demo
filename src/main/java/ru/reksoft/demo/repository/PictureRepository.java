package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.PictureEntity;

public interface PictureRepository extends JpaRepository<PictureEntity, Integer>, JpaSpecificationExecutor<PictureEntity> {

    boolean existsByName(String name);
}
