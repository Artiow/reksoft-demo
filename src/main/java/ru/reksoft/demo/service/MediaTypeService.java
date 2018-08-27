package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.MediaTypeDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.mapper.MediaTypeMapper;
import ru.reksoft.demo.repository.MediaTypeRepository;
import ru.reksoft.demo.service.generic.AbstractCRUDService;

import javax.validation.constraints.NotNull;

@Service
public class MediaTypeService {

    private MediaTypeRepository mediaTypeRepository;

    private MediaTypeMapper mediaTypeMapper;

    @Autowired
    public void setMediaTypeRepository(MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }

    @Autowired
    public void setMediaTypeMapper(MediaTypeMapper mediaTypeMapper) {
        this.mediaTypeMapper = mediaTypeMapper;
    }


    @Transactional(readOnly = true)
    public PageDTO<MediaTypeDTO> getList(@NotNull PageDividerDTO dividerDTO) {
        AbstractCRUDService.PageDivider divider = new AbstractCRUDService.PageDivider(dividerDTO);
        return new PageDTO<>(mediaTypeRepository.findAll(divider.getPageRequest()).map(mediaTypeMapper::toDTO));
    }
}
