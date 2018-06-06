package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.mapper.MediaMapper;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.util.MediaSearchType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
     * Returns media by id
     *
     * @return media
     */
    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        return mediaMapper.toDTO(mediaRepository.getOne(id));
    }


    public static class MediaFilter extends PageDivider implements Specification<MediaEntity> {

        private MediaSearchType searchType;
        private String searchString;

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
                if (searchString == null) {
                    throw new IllegalArgumentException("Search string must not be null!");
                }
                if (searchString.equals("")) {
                    throw new IllegalArgumentException("Search string must not be empty!");
                }

                this.searchType = searchType;
                this.searchString = searchString;
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
                switch (searchType) {
                    case BY_SINGER:
                        predicates.add(cb.and(root.join(MediaEntity_.album).join(AlbumEntity_.singer).get(SingerEntity_.name).in(searchString)));
                        break;
                    case BY_LABEL:
                        predicates.add(cb.and(root.join(MediaEntity_.album).join(AlbumEntity_.label).get(LabelEntity_.name).in(searchString)));
                        break;
                    case BY_ALBUM:
                        predicates.add(cb.and(root.join(MediaEntity_.album).get(AlbumEntity_.name).in(searchString)));
                        break;
                }
            }

            if (genreCodes != null) {
                predicates.add(cb.and(root.join(MediaEntity_.album).join(AlbumEntity_.genres).get(GenreEntity_.code).in(genreCodes)));
            }

            if (typeCodes != null) {
                predicates.add(cb.and(root.join(MediaEntity_.type).get(MediaTypeEntity_.code).in(typeCodes)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }
    }
}
