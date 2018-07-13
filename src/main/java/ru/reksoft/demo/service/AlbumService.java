package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.AlbumEntity_;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.shortcut.AlbumShortDTO;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.repository.AlbumRepository;
import ru.reksoft.demo.repository.LabelRepository;
import ru.reksoft.demo.repository.PictureRepository;
import ru.reksoft.demo.repository.SingerRepository;
import ru.reksoft.demo.service.generic.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.validation.constraints.NotNull;

@Service
public class AlbumService extends AbstractService<AlbumDTO> {

    private MessagesConfig messages;

    private LabelRepository labelRepository;
    private SingerRepository singerRepository;
    private PictureRepository pictureRepository;
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
    public void setPictureRepository(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
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
     * @throws ResourceNotFoundException if album not found by id
     */
    @Override
    @Transactional(readOnly = true)
    public AlbumDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return albumMapper.toDTO(albumRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.notExistById.message", id));
        }
    }

    /**
     * Save album.
     * Label and singer must exist.
     * All compositions will be created.
     * All genres must exist.
     *
     * @param albumDTO - album
     * @return saved entity id
     * @throws ResourceCannotCreateException - if album cannot created
     */
    @Override
    @Transactional
    public Integer create(@NotNull AlbumDTO albumDTO) throws ResourceCannotCreateException {
        Integer labelId = albumDTO.getLabel().getId();
        Integer singerId = albumDTO.getSinger().getId();
        Integer pictureId = albumDTO.getPicture().getId();
        String albumName = albumDTO.getName();

        if (!labelRepository.existsById(labelId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Label.notExistById.message", labelId
            ));
        } else if (!singerRepository.existsById(singerId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Singer.notExistById.message", singerId
            ));
        } else if (!pictureRepository.existsById(pictureId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Picture.notExistById.message", pictureId
            ));
        } else if (albumRepository.existsByNameAndSingerId(albumName, singerId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Album.alreadyExistByNameAndSingerId.message", albumName, singerId
            ));
        }

        AlbumEntity entity = albumMapper.toEntity(albumDTO);
        for (CompositionEntity composition : entity.getCompositions()) {
            composition.setAlbum(entity);
        }

        return albumRepository.save(entity).getId();
    }

    /**
     * Update album.
     *
     * @param id       - album id
     * @param albumDTO - new album data
     * @throws ResourceNotFoundException       - if album not found by id
     * @throws ResourceCannotUpdateException   - if album cannot updated
     * @throws ResourceOptimisticLockException - if album was already updated
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull AlbumDTO albumDTO)
            throws ResourceNotFoundException, ResourceCannotUpdateException, ResourceOptimisticLockException {

        Integer labelId = albumDTO.getLabel().getId();
        Integer singerId = albumDTO.getSinger().getId();
        Integer pictureId = albumDTO.getPicture().getId();

        if (!labelRepository.existsById(labelId)) {
            throw new ResourceCannotUpdateException(messages.getAndFormat(
                    "reksoft.demo.Label.notExistById.message", labelId
            ));
        } else if (!singerRepository.existsById(singerId)) {
            throw new ResourceCannotUpdateException(messages.getAndFormat(
                    "reksoft.demo.Singer.notExistById.message", singerId
            ));
        } else if (!pictureRepository.existsById(pictureId)) {
            throw new ResourceCannotUpdateException(messages.getAndFormat(
                    "reksoft.demo.Picture.notExistById.message", pictureId
            ));
        }

        try {
            AlbumEntity albumEntity = albumRepository.getOne(id);
            albumEntity.getCompositions().clear();
            albumRepository.flush();

            albumRepository.saveAndFlush(albumMapper.merge(albumEntity, albumMapper.toEntity(albumDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.notExistById.message", id));
        } catch (OptimisticLockException | OptimisticLockingFailureException e) {
            throw new ResourceOptimisticLockException(messages.get("reksoft.demo.Album.optimisticLock.message"), e);
        }
    }

    /**
     * Delete album.
     *
     * @param id - album id
     * @throws ResourceNotFoundException - if album not found by id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!albumRepository.existsById(id)) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Album.notExistById.message", id));
        }

        albumRepository.deleteById(id);
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
