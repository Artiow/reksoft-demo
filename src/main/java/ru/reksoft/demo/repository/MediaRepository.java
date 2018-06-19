package ru.reksoft.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reksoft.demo.domain.MediaEntity;

import java.util.List;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {

    Page<MediaEntity> findByAlbumSingerId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumLabelId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumId(Integer id, Pageable pageable);

    @Query("SELECT DISTINCT media FROM MediaEntity media INNER JOIN media.album album INNER JOIN album.genres genre WHERE genre.code IN (:codes)")
    Page<MediaEntity> findByGenreCodes(@Param("codes") List<String> codes, Pageable pageable);
}
