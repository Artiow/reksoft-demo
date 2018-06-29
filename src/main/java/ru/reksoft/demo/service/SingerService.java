package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.domain.SingerEntity_;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.mapper.SingerMapper;
import ru.reksoft.demo.repository.SingerRepository;
import ru.reksoft.demo.service.generic.AbstractService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class SingerService extends AbstractService<SingerDTO> {

    private MessagesConfig messages;

    private SingerRepository singerRepository;

    private SingerMapper singerMapper;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

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
    public SingerDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return singerMapper.toDTO(singerRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Singer.existById.message", id));
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
    public Integer create(@NotNull SingerDTO singerDTO) throws ResourceCannotCreateException {
        if (singerRepository.existsByName(singerDTO.getName())) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Singer.existByName.message", singerDTO.getName()));
        }

        return singerRepository.save(singerMapper.toEntity(singerDTO)).getId();
    }

    /**
     * Update singer.
     *
     * @param id        - singer id
     * @param singerDTO - new singer data
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull SingerDTO singerDTO) throws ResourceNotFoundException {
        try {
            singerRepository.save(singerMapper.merge(singerRepository.getOne(id), singerMapper.toEntity(singerDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Singer.existById.message", id));
        }
    }

    /**
     * Delete label.
     *
     * @param id - label id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!singerRepository.existsById(id)) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Singer.existById.message", id));
        }

        singerRepository.deleteById(id);
    }


    public static class SingerSearcher extends StringSearcher<SingerEntity> {

        public SingerSearcher(StringSearcherDTO dto) {
            super(dto, SingerEntity_.name);
        }
    }
}
