package ru.reksoft.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.reksoft.demo.domain.MediaEntity;

public interface MediaRepository extends JpaRepository<MediaEntity, Integer>, JpaSpecificationExecutor<MediaEntity> {

    Page<MediaEntity> findByAlbumSingerId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumLabelId(Integer id, Pageable pageable);

    Page<MediaEntity> findByAlbumId(Integer id, Pageable pageable);

    @Query("SELECT album.media FROM AlbumEntity album INNER JOIN album.genres genre WHERE genre.id = :id")
    Page<MediaEntity> findByGenreId(@Param("id") Integer id, Pageable pageable);

    @Query("SELECT album.media FROM AlbumEntity album INNER JOIN album.genres genre WHERE genre.code = :code")
    Page<MediaEntity> findByGenreCode(@Param("code") String code, Pageable pageable);
}
