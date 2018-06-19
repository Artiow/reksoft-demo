package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.mapper.MediaMapper;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.util.MediaSearchType;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MediaService extends AbstractService {

    private MediaRepository mediaRepository;

    private MediaMapper mediaMapper;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Autowired
    public void setMediaMapper(MediaMapper mediaMapper) {
        this.mediaMapper = mediaMapper;
    }


    /**
     * Returns page with filtered media
     *
     * @param filterDTO - filter for media
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaList(@NotNull MediaFilterDTO filterDTO) {
        MediaFilter filter = new MediaFilter(filterDTO);
        return new PageDTO<>(mediaRepository.findAll(filter, filter.getPageRequest()).map(mediaMapper::toShortDTO));
    }


    /**
     * Returns page with media by album by id
     *
     * @param id - album id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListBySinger(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbumSingerId(id, pd.getPageRequest()).map(mediaMapper::toShortDTO));
    }

    /**
     * Returns page with media by label by id
     *
     * @param id - label id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByLabel(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbumLabelId(id, pd.getPageRequest()).map(mediaMapper::toShortDTO));
    }

    /**
     * Returns page with media by album by id
     *
     * @param id - album id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByAlbumId(id, pd.getPageRequest()).map(mediaMapper::toShortDTO));
    }

    /**
     * Returns page with media by album genre by id
     *
     * @param id - genre id
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByGenre(@NotNull Integer id, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByGenreId(id, pd.getPageRequest()).map(mediaMapper::toShortDTO));
    }

    /**
     * Returns page with media by album genre by code
     *
     * @param code - genre code
     * @param pdDTO - page divider
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByGenre(@NotNull String code, @NotNull PageDividerDTO pdDTO) {
        PageDivider pd = new PageDivider(pdDTO);
        return new PageDTO<>(mediaRepository.findByGenreCode(code, pd.getPageRequest()).map(mediaMapper::toShortDTO));
    }


    /**
     * Returns media by id
     *
     * @return media
     */
    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        return mediaMapper.toDTO(mediaRepository.getOne(id));
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
                if ((searchString != null) && (!searchString.equals(""))) {
                    this.searchWords = searchString.trim().toLowerCase().split(" ");
                    this.searchType = searchType;
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
                predicates.add(searchByStringPredicate(root, query, cb));
            }
            if (genreCodes != null) {
                predicates.add(searchByGenrePredicate(root, query, cb));
            }
            if (typeCodes != null) {
                predicates.add(searchByTypePredicate(root, query, cb));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }

        private Predicate searchByStringPredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
            for (String searchWord: searchWords) {
                String[] words = new String[]{(searchWord + " %"), ("% " + searchWord + " %"), ("% " + searchWord)};

                Collection<Predicate> occurrence = new ArrayList<>();
                for (String word : words) {
                    occurrence.add(cb.like(cb.lower(sought), word));
                }

                occurrences.add(cb.or(occurrence.toArray(new Predicate[0])));
            }

            return cb.and(occurrences.toArray(new Predicate[0]));
        }

        private Predicate searchByGenrePredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return root.join(MediaEntity_.album).join(AlbumEntity_.genres).get(GenreEntity_.code).in(genreCodes);
        }

        private Predicate searchByTypePredicate(Root<MediaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return root.join(MediaEntity_.type).get(MediaTypeEntity_.code).in(typeCodes);
        }
    }
}
