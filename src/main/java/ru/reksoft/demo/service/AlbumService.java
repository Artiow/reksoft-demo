package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
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

    private MessagesConfig messages;

    private LabelRepository labelRepository;
    private SingerRepository singerRepository;
    private AlbumRepository albumRepository;

    private AlbumMapper albumMapper;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setLabelRepository(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

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
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.existById.message", id));
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
        Integer labelId = albumDTO.getLabel().getId();
        Integer singerId = albumDTO.getSinger().getId();
        String albumName = albumDTO.getName();

        if (!labelRepository.existsById(labelId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Label.existById.message", labelId
            ));
        } else if (!singerRepository.existsById(singerId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Singer.existById.message", singerId
            ));
        } else if (albumRepository.existsByNameAndSingerId(albumName, singerId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Album.existByNameAndSingerId.message", albumName, singerId
            ));
        }

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
        try {
            AlbumEntity albumEntity = albumRepository.getOne(id);
            albumEntity.getCompositions().clear();
            albumRepository.flush();

            albumRepository.save(albumMapper.merge(albumEntity, albumMapper.toEntity(albumDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.existById.message", id));
        }
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
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.existById.message", id));
        }

        albumRepository.deleteById(id);
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
