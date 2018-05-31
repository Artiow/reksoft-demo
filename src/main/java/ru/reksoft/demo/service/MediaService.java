package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaFilterDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.dto.PageDTO;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.service.util.MediaFilter;

@Service
public class MediaService {

    private MediaRepository mediaRepository;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    /**
     * Returns page with filtered media
     *
     * @param filterDTO - filter for media
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaList(MediaFilterDTO filterDTO) {
        MediaFilter filter;
        if (filterDTO != null)
            filter = new MediaFilter(filterDTO);
        else
            filter = new MediaFilter();

        return new PageDTO<>(mediaRepository.findAll(filter, filter.getPageRequest()).map(MediaShortDTO::new));
    }

    /**
     * Returns page with media by singer by id
     *
     * @param singerId - singer id
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListBySinger(Integer singerId) {
        return null;
    }

    /**
     * Returns page with media by label by id
     *
     * @param labelId - label id
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByLabel(Integer labelId) {
        return null;
    }

    /**
     * Returns page with media by album by id
     *
     * @param albumId - album id
     * @return media page
     */
    @Transactional(readOnly = true)
    public PageDTO<MediaShortDTO> getMediaListByAlbum(Integer albumId) {
        return null;
    }

    /**
     * Returns media by id
     *
     * @return media
     */
    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        return new MediaDTO(mediaRepository.getOne(id));
    }
}
