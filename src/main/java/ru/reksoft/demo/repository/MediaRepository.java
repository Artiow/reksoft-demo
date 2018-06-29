package ru.reksoft.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.reksoft.demo.domain.MediaEntity;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {

    boolean existsByAlbumIdAndTypeId(Integer albumId, Integer typeId);

    Page<MediaEntity> findByAlbumSingerId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumLabelId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumId(Integer id, Pageable pageable);
}
