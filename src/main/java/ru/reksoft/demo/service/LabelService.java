package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reksoft.demo.mapper.LabelMapper;
import ru.reksoft.demo.repository.LabelRepository;

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
}
