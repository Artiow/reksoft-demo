package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.AlbumEntity_;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.AlbumShortDTO;
import ru.reksoft.demo.dto.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.repository.AlbumRepository;

import javax.validation.constraints.NotNull;

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


    /**
     * Returns page with searched albums
     *
     * @param searcherDTO - searcher for album
     * @return album page
     */
    @Transactional(readOnly = true)
    public PageDTO<AlbumShortDTO> getAlbumList(@NotNull StringSearcherDTO searcherDTO) {
        AlbumSearcher searcher = new AlbumSearcher(searcherDTO);
        return new PageDTO<>(albumRepository.findAll(searcher, searcher.getPageRequest()).map(albumMapper::toShortDTO));
    }


    /**
     * Save album
     *
     * @param dto - album
     * @return saved entity
     */
    @Transactional
    public AlbumDTO saveAlbum(@NotNull AlbumDTO dto) {
        return null; //TODO: save!
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
