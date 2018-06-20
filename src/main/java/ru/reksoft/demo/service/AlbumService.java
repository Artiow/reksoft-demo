package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.AlbumEntity;
import ru.reksoft.demo.domain.AlbumEntity_;
import ru.reksoft.demo.domain.CompositionEntity;
import ru.reksoft.demo.domain.GenreEntity;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.AlbumMapper;
import ru.reksoft.demo.repository.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Service
public class AlbumService extends AbstractService {

    private LabelRepository labelRepository;
    private SingerRepository singerRepository;
    private AlbumRepository albumRepository;
    private GenreRepository genreRepository;

    private AlbumMapper albumMapper;

    @Autowired
    public void setLabelRepository(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Autowired
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
     * Save album.
     *
     * Label and singer must exist.
     * All compositions will be created.
     * All genres must exist.
     *
     * @param dto - album
     * @return saved entity
     */
    @Transactional
    public AlbumDTO saveAlbum(@NotNull AlbumDTO dto) {
        AlbumEntity entity = albumMapper.toEntity(dto);
        entity.setLabel(labelRepository.getOne(dto.getLabel().getId()));
        entity.setSinger(singerRepository.getOne(dto.getSinger().getId()));

        ArrayList<GenreEntity> genres = new ArrayList<>(entity.getGenres());
        entity.getGenres().clear();
        for (GenreEntity genre: genres) {
            entity.getGenres().add(genreRepository.findByCode(genre.getCode())); //todo: code or id?
        }

        for (CompositionEntity composition: entity.getCompositions()) {
            composition.setAlbum(entity);
        }

        return albumMapper.toDTO(albumRepository.save(entity)); //todo: put in a separate read transaction?
    }


    public static class AlbumSearcher extends StringSearcher<AlbumEntity> {

        public AlbumSearcher(StringSearcherDTO dto) {
            super(dto, AlbumEntity_.name);
        }
    }
}
