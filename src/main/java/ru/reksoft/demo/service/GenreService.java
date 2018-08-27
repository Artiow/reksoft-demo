package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.GenreDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.mapper.GenreMapper;
import ru.reksoft.demo.repository.GenreRepository;
import ru.reksoft.demo.service.generic.AbstractCRUDService;

import javax.validation.constraints.NotNull;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    private GenreMapper genreMapper;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setGenreMapper(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }


    @Transactional(readOnly = true)
    public PageDTO<GenreDTO> getList(@NotNull PageDividerDTO dividerDTO) {
        AbstractCRUDService.PageDivider divider = new AbstractCRUDService.PageDivider(dividerDTO);
        return new PageDTO<>(genreRepository.findAll(divider.getPageRequest()).map(genreMapper::toDTO));
    }
}
