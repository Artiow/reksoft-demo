package ru.reksoft.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.MediaEntity;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {
    Page<MediaEntity> findByAlbum_Singer_Id(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbum_Label_Id(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbum_Id(Integer id, Pageable pageable);
}
