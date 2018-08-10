package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.messages.MessageContainer;
import ru.reksoft.demo.domain.LabelEntity;
import ru.reksoft.demo.domain.LabelEntity_;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.mapper.LabelMapper;
import ru.reksoft.demo.repository.LabelRepository;
import ru.reksoft.demo.service.generic.AbstractService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.validation.constraints.NotNull;

@Service
public class LabelService extends AbstractService<LabelDTO> {

    private MessageContainer messages;

    private LabelRepository labelRepository;

    private LabelMapper labelMapper;

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

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
     * @throws ResourceNotFoundException - if label not found by id
     */
    @Override
    @Transactional(readOnly = true)
    public LabelDTO get(@NotNull Integer id) throws ResourceNotFoundException {
        try {
            return labelMapper.toDTO(labelRepository.getOne(id));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Label.notExistById.message", id), e);
        }
    }

    /**
     * Save label.
     *
     * @param labelDTO - label
     * @return saved entity id
     * @throws ResourceCannotCreateException - if label cannot created
     */
    @Override
    @Transactional
    public Integer create(@NotNull LabelDTO labelDTO) throws ResourceCannotCreateException {
        if (labelRepository.existsByName(labelDTO.getName())) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Label.alreadyExistByName.message", labelDTO.getName()));
        }

        return labelRepository.save(labelMapper.toEntity(labelDTO)).getId();
    }

    /**
     * Update label.
     *
     * @param id       - label id
     * @param labelDTO - new label data
     * @throws ResourceNotFoundException       - if label not found by id
     * @throws ResourceOptimisticLockException - if label was already updated
     */
    @Override
    @Transactional
    public void update(@NotNull Integer id, @NotNull LabelDTO labelDTO)
            throws ResourceNotFoundException, ResourceOptimisticLockException {

        try {
            labelRepository.saveAndFlush(labelMapper.merge(labelRepository.getOne(id), labelMapper.toEntity(labelDTO)));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Label.notExistById.message", id));
        } catch (OptimisticLockException | OptimisticLockingFailureException e) {
            throw new ResourceOptimisticLockException(messages.get("reksoft.demo.Label.optimisticLock.message"), e);
        }
    }

    /**
     * Delete label.
     *
     * @param id - label id
     * @throws ResourceNotFoundException - if label not found by id
     */
    @Override
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!labelRepository.existsById(id)) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Label.notExistById.message", id));
        }

        labelRepository.deleteById(id);
    }


    public static class LabelSearcher extends StringSearcher<LabelEntity> {

        public LabelSearcher(StringSearcherDTO dto) {
            super(dto, LabelEntity_.name);
        }
    }
}
