package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.AlbumEntity_;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.mapper.CompositionMapper;
import ru.reksoft.demo.mapper.GenreMapper;
import ru.reksoft.demo.repository.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Service
public class AlbumService extends AbstractService {

    private LabelService labelService;
    private SingerService singerService;

    private AlbumRepository albumRepository;
    private CompositionRepository compositionRepository;
    private GenreRepository genreRepository;

    private AlbumMapper albumMapper;
    private CompositionMapper compositionMapper;
    private GenreMapper genreMapper;

    @Autowired
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setAlbumMapper(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    @Autowired
    public void setCompositionMapper(CompositionMapper compositionMapper) {
        this.compositionMapper = compositionMapper;
    }

    @Autowired
    public void setGenreMapper(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
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
     * Save album.
     *
     * If label or singer do not exist then they will be created.
     * All compositions will be created.
     * All genres must exist.
     *
     * @param dto - album
     * @return saved entity
     */
    @Transactional
    public AlbumDTO saveAlbum(@NotNull AlbumDTO dto) {
        LabelDTO labelDTO = dto.getLabel();
        if (labelDTO.getName() != null)  {
            dto.setLabel(labelService.saveLabel(labelDTO));
        }

        SingerDTO singerDTO = dto.getSinger();
        if (singerDTO.getName() != null)  {
            dto.setSinger(singerService.saveSinger(singerDTO));
        }

        Collection<CompositionEntity> compositions = compositionMapper.toEntity(dto.getCompositions());

        dto.setCompositions(null);
        AlbumEntity savedEntity = albumRepository.save(albumMapper.toEntity(dto));
        for (CompositionEntity e: compositions) {
            e.setAlbum(savedEntity);
        }

//        TODO: save composition! fix interval parsing!
//        compositionRepository.saveAll(compositions);
        return albumMapper.toDTO(savedEntity);
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
