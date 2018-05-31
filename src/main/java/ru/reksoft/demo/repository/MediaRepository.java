package ru.reksoft.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.MediaEntity;

import java.util.List;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {
    List<MediaEntity> findByAlbum_Singer_Id(Integer singerId);

    List<MediaEntity> findByAlbum_Label_Id(Integer labelId);

    List<MediaEntity> findByAlbum_Id(Integer albumId);
}
