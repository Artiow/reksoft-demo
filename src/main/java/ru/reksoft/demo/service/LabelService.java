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

import javax.validation.constraints.NotNull;

@Service
public class LabelService extends AbstractService {

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
     * Returns page with searched labels
     *
     * @param searcherDTO - searcher for label
     * @return label page
     */
    @Transactional(readOnly = true)
    public PageDTO<LabelDTO> getLabelList(@NotNull StringSearcherDTO searcherDTO) {
        LabelSearcher searcher = new LabelSearcher(searcherDTO);
        return new PageDTO<>(labelRepository.findAll(searcher, searcher.getPageRequest()).map(labelMapper::toDTO));
    }


    /**
     * Save label.
     * Returns an existing one if it finds by name.
     *
     * @param dto - label
     * @return saved entity
     */
    @Transactional
    public LabelDTO createLabel(@NotNull LabelDTO dto) {
        return labelMapper.toDTO(labelRepository.save(labelMapper.toEntity(dto)));
    }


    public static class LabelSearcher extends StringSearcher<LabelEntity> {

        public LabelSearcher(StringSearcherDTO dto) {
            super(dto, LabelEntity_.name);
        }
    }
}
