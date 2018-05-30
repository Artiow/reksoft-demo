package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.repository.MediaRepository;

@Service
public class MediaService {

    private MediaRepository mediaRepository;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    /**
     * Return list of media
     *
     * @return list of media
     */
    @Transactional(readOnly = true)
    public Page<MediaShortDTO> getMediaList() {
        return mediaRepository.findAll(Pageable.unpaged()).map(MediaShortDTO::new); //TODO: add pagination!
    }

    /**
     * Return media by id
     *
     * @return media
     */
    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        return new MediaDTO(mediaRepository.getOne(id)); //TODO: add NullPointerException handling!
    }
}
