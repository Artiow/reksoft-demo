package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer>, JpaSpecificationExecutor<GenreEntity> {

}
