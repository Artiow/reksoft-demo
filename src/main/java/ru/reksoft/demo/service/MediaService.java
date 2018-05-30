package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.util.MediaFilter;

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
     * @param filter - filter for media
     * @return media page
     */
    @Transactional(readOnly = true)
    public Page<MediaShortDTO> getMediaList(MediaFilter filter) {
        return mediaRepository.findAll(filter, filter.getPageRequest()).map(MediaShortDTO::new);
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
