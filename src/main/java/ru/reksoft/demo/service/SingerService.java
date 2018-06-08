package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reksoft.demo.mapper.SingerMapper;
import ru.reksoft.demo.repository.SingerRepository;

@Service
public class SingerService extends AbstractService {

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
}
