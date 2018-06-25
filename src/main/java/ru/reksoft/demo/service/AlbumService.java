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
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class AlbumService extends AbstractService<AlbumDTO> {

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
     * Returns page with searched albums.
     *
     * @param searcherDTO - searcher for album
     * @return album page
     */
    @Transactional(readOnly = true)
    public PageDTO<AlbumShortDTO> getList(@NotNull StringSearcherDTO searcherDTO) {
        AlbumSearcher searcher = new AlbumSearcher(searcherDTO);
        return new PageDTO<>(albumRepository.findAll(searcher, searcher.getPageRequest()).map(albumMapper::toShortDTO));
    }

    /**
     * Returns album by id.
     *
     * @param id - album id
     * @return album
     */
    @Override
    @Transactional(readOnly = true)
    public AlbumDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return albumMapper.toDTO(albumRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Album with id %d does not exist!", id));
        }
    }

    /**
     * Save album.
     *
     * Label and singer must exist.
     * All compositions will be created.
     * All genres must exist.
     *
     * @param albumDTO - album
     * @return saved entity id
     */
    @Override
    @Transactional
    public Integer create(@NotNull AlbumDTO albumDTO) throws ResourceCannotCreateException {
        AlbumEntity entity = albumMapper.toEntity(albumDTO);
        for (CompositionEntity composition: entity.getCompositions()) {
            composition.setAlbum(entity);
        }

        return albumRepository.save(entity).getId();
    }

    /**
     * Update album.
     *
     * @param id - album id
     * @param albumDTO - new album data
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull AlbumDTO albumDTO) throws ResourceNotFoundException {
        //todo: update!
    }

    /**
     * Delete album.
     *
     * @param id - album id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!albumRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Album with id %d does not exist!", id));
        }

        albumRepository.deleteById(id);
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
