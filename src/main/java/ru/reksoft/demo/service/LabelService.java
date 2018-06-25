package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.domain.LabelEntity_;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.LabelMapper;
import ru.reksoft.demo.repository.LabelRepository;
import ru.reksoft.demo.service.generic.AbstractService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class LabelService extends AbstractService<LabelDTO> {

    private LabelRepository labelRepository;

    private LabelMapper labelMapper;

    @Autowired
    public void setLabelRepository(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Autowired
    public void setLabelMapper(LabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }


    /**
     * Returns page with searched labels.
     *
     * @param searcherDTO - searcher for label
     * @return label page
     */
    @Transactional(readOnly = true)
    public PageDTO<LabelDTO> getList(@NotNull StringSearcherDTO searcherDTO) {
        LabelSearcher searcher = new LabelSearcher(searcherDTO);
        return new PageDTO<>(labelRepository.findAll(searcher, searcher.getPageRequest()).map(labelMapper::toDTO));
    }

    /**
     * Returns label.
     *
     * @param id - label id
     * @return found label
     */
    @Override
    @Transactional(readOnly = true)
    public LabelDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return labelMapper.toDTO(labelRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Label with id %d does not exist!", id));
        }
    }

    /**
     * Save label.
     *
     * @param labelDTO - label
     * @return saved entity id
     */
    @Override
    @Transactional
    public Integer create(@NotNull LabelDTO labelDTO) throws ResourceCannotCreateException {
        if (labelRepository.findByName(labelDTO.getName()) != null) {
            throw new ResourceCannotCreateException(String.format("Label with name \'%s\' already exist!", labelDTO.getName()));
        }

        return labelRepository.save(labelMapper.toEntity(labelDTO)).getId();
    }

    /**
     * Update label.
     *
     * @param id - label id
     * @param labelDTO - new label data
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull LabelDTO labelDTO) throws ResourceNotFoundException {
        try {
            labelRepository.save(labelMapper.merge(labelRepository.getOne(id), labelMapper.toEntity(labelDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Label with id %d does not exist!", id));
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
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Label with id %d does not exist!", id));
        }

        labelRepository.deleteById(id);
    }


    public static class LabelSearcher extends StringSearcher<LabelEntity> {

        public LabelSearcher(StringSearcherDTO dto) {
            super(dto, LabelEntity_.name);
        }
    }
}
