package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reksoft.demo.domain.MediaEntity;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer> {

}
