package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.repository.AlbumRepository;

@Service
public class AlbumService extends AbstractService {

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
}
