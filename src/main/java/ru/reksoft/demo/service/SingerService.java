package ru.reksoft.demo.service;

import javassist.NotFoundException;
import javassist.tools.reflect.CannotCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.domain.SingerEntity_;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.SingerMapper;
import ru.reksoft.demo.repository.SingerRepository;
import ru.reksoft.demo.service.generic.AbstractService;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class SingerService extends AbstractService<SingerDTO> {

    private SingerRepository singerRepository;

    private SingerMapper singerMapper;
    
    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }
    
    @Autowired
    public void setSingerMapper(SingerMapper singerMapper) {
        this.singerMapper = singerMapper;
    }


    /**
     * Returns page with searched singers.
     *
     * @param searcherDTO - searcher for singer
     * @return singer page
     */
    @Transactional(readOnly = true)
    public PageDTO<SingerDTO> getList(@NotNull StringSearcherDTO searcherDTO) {
        SingerSearcher searcher = new SingerSearcher(searcherDTO);
        return new PageDTO<>(singerRepository.findAll(searcher, searcher.getPageRequest()).map(singerMapper::toDTO));
    }

    /**
     * Returns singer.
     *
     * @param id - singer id
     * @return found singer
     */
    @Override
    @Transactional(readOnly = true)
    public SingerDTO get(@NotNull Integer id) throws NotFoundException {
        try {
            return singerMapper.toDTO(singerRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(String.format("Singer with id %d does not exist!", id));
        }
    }

    /**
     * Save singer.
     *
     * @param singerDTO - singer
     * @return saved entity id
     */
    @Override
    @Transactional
    public Integer create(@NotNull SingerDTO singerDTO) throws CannotCreateException {
        try {
            return singerRepository.save(singerMapper.toEntity(singerDTO)).getId();
        } catch (DataAccessException e) {
            throw new CannotCreateException(String.format("Cannot create singer %s!", singerDTO.getName()));
        }
    }

    /**
     * Update singer.
     *
     * @param id - singer id
     * @param singerDTO - new singer data
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull SingerDTO singerDTO) throws NotFoundException {
        try {
            singerRepository.save(singerMapper.merge(singerRepository.getOne(id), singerMapper.toEntity(singerDTO)));
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(String.format("Singer with id %d does not exist!", id));
        }
    }

    /**
     * Delete label.
     *
     * @param id - label id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws NotFoundException {
        if (!singerRepository.existsById(id)) {
            throw new NotFoundException(String.format("Singer with id %d does not exist!", id));
        }

        singerRepository.deleteById(id);
    }


    public static class SingerSearcher extends StringSearcher<SingerEntity> {

        public SingerSearcher(StringSearcherDTO dto) {
            super(dto, SingerEntity_.name);
        }
    }
}
