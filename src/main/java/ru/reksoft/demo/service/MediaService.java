package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.MediaEntity;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.repository.MediaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {

    private MediaRepository mediaRepository;

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Transactional(readOnly = true)
    public List<MediaShortDTO> getMediaList() {
        List<MediaEntity> entities = mediaRepository.findAll();
        List<MediaShortDTO> dtos = new ArrayList<>();
        for (MediaEntity entity: entities) dtos.add(new MediaShortDTO(entity));
        return dtos;
    }

    @Transactional(readOnly = true)
    public MediaDTO getMedia(Integer id) {
        MediaEntity entity = mediaRepository.getOne(id);
        return new MediaDTO(entity);
    }
}
