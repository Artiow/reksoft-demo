package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.MediaEntity;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {

}
