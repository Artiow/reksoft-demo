package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.AlbumEntity_;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.repository.*;
import ru.reksoft.demo.service.generic.AbstractService;

import javax.validation.constraints.NotNull;

@Service
public class AlbumService extends AbstractService {

    private AlbumRepository albumRepository;

    private AlbumMapper albumMapper;

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Autowired
    public void setAlbumMapper(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    /**
     * Returns page with searched albums
     *
     * @param searcherDTO - searcher for album
     * @return album page
     */
    @Transactional(readOnly = true)
    public PageDTO<AlbumShortDTO> getAlbumList(@NotNull StringSearcherDTO searcherDTO) {
        AlbumSearcher searcher = new AlbumSearcher(searcherDTO);
        return new PageDTO<>(albumRepository.findAll(searcher, searcher.getPageRequest()).map(albumMapper::toShortDTO));
    }


    /**
     * Save album.
     *
     * Label and singer must exist.
     * All compositions will be created.
     * All genres must exist.
     *
     * @param dto - album
     * @return saved entity id
     */
    @Transactional
    public Integer createAlbum(@NotNull AlbumDTO dto) {
        AlbumEntity entity = albumMapper.toEntity(dto);
        for (CompositionEntity composition: entity.getCompositions()) {
            composition.setAlbum(entity);
        }

        return albumRepository.save(entity).getId();
    }


    /**
     * Returns album by id
     *
     * @return album
     */
    @Transactional(readOnly = true)
    public AlbumDTO getAlbum(@NotNull Integer id) {
        return albumMapper.toDTO(albumRepository.getOne(id));
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
