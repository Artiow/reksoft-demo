package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.shortcut.MediaShortDTO;
import ru.reksoft.demo.mapper.MediaMapper;
import ru.reksoft.demo.repository.AlbumRepository;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.repository.MediaTypeRepository;
import ru.reksoft.demo.service.generic.*;
import ru.reksoft.demo.util.MediaSearchType;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MediaService extends AbstractCRUDService<MediaDTO> {

    private MessageContainer messages;

    private AlbumRepository albumRepository;
    private MediaTypeRepository mediaTypeRepository;
    private MediaRepository mediaRepository;

    private MediaMapper mediaMapper;

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Autowired
    public void setMediaTypeRepository(MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Autowired
    public void setMediaMapper(MediaMapper mediaMapper) {
        this.mediaMapper = mediaMapper;
    }


    /**
     * Returns page with filtered media.
     *
     * @param filterDTO - filter for media
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getListByFilter(@NotNull MediaFilterDTO filterDTO) {
        MediaFilter filter = new MediaFilter(filterDTO);
        return new PageDTO<>(mediaRepository.findAll(filter, filter.getPageRequest()).map(mediaMapper::toShortDTO));
    }

    /**
     * Returns page with media by one of attribute (album, label, singer) id.
     *
     * @param attributeType - attribute type
     * @param attributeId   - attribute id
     * @param pageDivider   - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getListByAttribute(@NotNull MediaSearchType attributeType, @NotNull Integer attributeId, @NotNull PageDividerDTO pageDivider) {
        Pageable request = new PageDivider(pageDivider).getPageRequest();
        Page<MediaEntity> page = null;
        switch (attributeType) {
            case BY_ALBUM:
                page = mediaRepository.findByAlbumId(attributeId, request);
                break;
            case BY_LABEL:
                page = mediaRepository.findByAlbumLabelId(attributeId, request);
                break;
            case BY_SINGER:
                page = mediaRepository.findByAlbumSingerId(attributeId, request);
                break;
        }

        return new PageDTO<>(page.map(mediaMapper::toShortDTO));
    }

    /**
     * Returns media by id.
     *
     * @param id - media id
     * @return media
     * @throws ResourceNotFoundException - if media not found by id
     */
    @Override
    @Transactional(readOnly = true)
    public MediaDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return mediaMapper.toDTO(mediaRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Media.notExistById.message", id), e);
        }
    }

    /**
     * Save media.
     * Media type and album must exist.
     *
     * @param mediaDTO - media
     * @return saved entity id
     * @throws ResourceCannotCreateException - if media cannot created
     */
    @Override
    @Transactional
    public Integer create(@NotNull MediaDTO mediaDTO) throws ResourceCannotCreateException {
        Integer typeId = mediaDTO.getType().getId();
        Integer albumId = mediaDTO.getAlbum().getId();

        if (!albumRepository.existsById(albumId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Album.notExistById.message", albumId
            ));
        } else if (!mediaTypeRepository.existsById(typeId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.MediaType.notExistById.message", typeId
            ));
        } else if (mediaRepository.existsByAlbumIdAndTypeId(albumId, typeId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat(
                    "reksoft.demo.Media.alreadyExistByIdAndMediaTypeId.message", albumId, typeId
            ));
        }

        return mediaRepository.save(mediaMapper.toEntity(mediaDTO)).getId();
    }

    /**
     * Update media.
     *
     * @param id       - media id
     * @param mediaDTO - new media data
     * @throws ResourceNotFoundException       - if media not found by id
     * @throws ResourceCannotUpdateException   - if media cannot updated
     * @throws ResourceOptimisticLockException - if media was already updated
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull MediaDTO mediaDTO)
            throws ResourceNotFoundException, ResourceCannotUpdateException, ResourceOptimisticLockException {

        Integer typeId = mediaDTO.getType().getId();
        Integer albumId = mediaDTO.getAlbum().getId();

        if (!albumRepository.existsById(albumId)) {
            throw new ResourceCannotUpdateException(messages.getAndFormat(
                    "reksoft.demo.Album.notExistById.message", albumId
            ));
        } else if (!mediaTypeRepository.existsById(typeId)) {
            throw new ResourceCannotUpdateException(messages.getAndFormat(
                    "reksoft.demo.MediaType.notExistById.message", typeId
            ));
        }

        try {
            mediaRepository.saveAndFlush(mediaMapper.merge(mediaRepository.getOne(id), mediaMapper.toEntity(mediaDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Media.notExistById.message", id));
        } catch (OptimisticLockException | OptimisticLockingFailureException e) {
            throw new ResourceOptimisticLockException(messages.get("reksoft.demo.Media.optimisticLock.message"), e);
        }
    }

    /**
     * Delete media.
     *
     * @param id - media id
     * @throws ResourceNotFoundException- if media not found by id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!mediaRepository.existsById(id)) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Media.notExistById.message", id));
        }

        mediaRepository.deleteById(id);
    }


    public static class MediaFilter extends PageDivider implements Specification<MediaEntity> {

        private String[] searchWords;
        private MediaSearchType searchType;

        private Collection<String> typeCodes;
        private Collection<String> genreCodes;


        public MediaFilter(MediaFilterDTO dto) {
            super(dto);

            configureSearchByString(dto);
            configureSearchByGenres(dto);
            configureSearchByType(dto);
        }


        private void configureSearchByString(MediaFilterDTO dto) {
            MediaSearchType searchType = dto.getSearchType();
            if (searchType != null) {
                String searchString = dto.getSearchString();
                if (searchString != null) {
                    searchString = searchString.trim();
                    if (!searchString.equals("")) {
                        this.searchWords = searchString.trim().toLowerCase().split(" ");
                        this.searchType = searchType;
                    }
                }
            }
        }

        private void configureSearchByGenres(MediaFilterDTO dto) {
            Collection<String> genreCodes = dto.getGenreCodes();
            if ((genreCodes != null) && (!genreCodes.isEmpty())) {
                this.genreCodes = new ArrayList<>(genreCodes);
            }
        }

        private void configureSearchByType(MediaFilterDTO dto) {
            Collection<String> typeCodes = dto.getTypeCodes();
            if ((typeCodes != null) && (!typeCodes.isEmpty())) {
                this.typeCodes = new ArrayList<>(typeCodes);
            }
        }


        @Override
        public Predicate toPredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Collection<Predicate> predicates = new ArrayList<>();
            if (searchType != null) {
                predicates.add(searchByStringPredicate(root, cb));
            }
            if (genreCodes != null) {
                predicates.add(searchByGenrePredicate(root, cb));
            }
            if (typeCodes != null) {
                predicates.add(searchByTypePredicate(root, cb));
            }

            return query
                    .where(cb.and(predicates.toArray(new Predicate[0])))
                    .distinct(true).getRestriction();
        }

        private Predicate searchByStringPredicate(Root<MediaEntity> root, CriteriaBuilder cb) {
            Join<MediaEntity, AlbumEntity> album = root.join(MediaEntity_.album);
            Expression<String> sought = null;
            switch (searchType) {
                case BY_SINGER:
                    sought = album.join(AlbumEntity_.singer).get(SingerEntity_.name);
                    break;
                case BY_LABEL:
                    sought = album.join(AlbumEntity_.label).get(LabelEntity_.name);
                    break;
                case BY_ALBUM:
                    sought = album.get(AlbumEntity_.name);
                    break;
            }

            Collection<Predicate> occurrences = new ArrayList<>();
            for (String searchWord : searchWords) {
                String[] words = new String[]{(searchWord + " %"), ("% " + searchWord + " %"), ("% " + searchWord)};

                Collection<Predicate> occurrence = new ArrayList<>();
                for (String word : words) {
                    occurrence.add(cb.like(cb.lower(sought), word));
                }

                occurrences.add(cb.or(occurrence.toArray(new Predicate[0])));
            }

            return cb.and(occurrences.toArray(new Predicate[0]));
        }

        private Predicate searchByGenrePredicate(Root<MediaEntity> root, CriteriaBuilder cb) {
            return root.join(MediaEntity_.album).join(AlbumEntity_.genres).get(GenreEntity_.code).in(genreCodes);
        }

        private Predicate searchByTypePredicate(Root<MediaEntity> root, CriteriaBuilder cb) {
            return root.join(MediaEntity_.type).get(MediaTypeEntity_.code).in(typeCodes);
        }
    }
}
