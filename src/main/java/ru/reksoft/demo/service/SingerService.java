package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.domain.SingerEntity;
import ru.reksoft.demo.domain.SingerEntity_;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.mapper.SingerMapper;
import ru.reksoft.demo.repository.SingerRepository;

import javax.validation.constraints.NotNull;

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


    /**
     * Returns page with searched singers
     *
     * @param searcherDTO - searcher for singer
     * @return singer page
     */
    @Transactional(readOnly = true)
    public PageDTO<SingerDTO> getSingerList(@NotNull StringSearcherDTO searcherDTO) {
        SingerSearcher searcher = new SingerSearcher(searcherDTO);
        return new PageDTO<>(singerRepository.findAll(searcher, searcher.getPageRequest()).map(singerMapper::toDTO));
    }


    /**
     * Save singer.
     * Returns an existing one if it finds by name.
     *
     * @param dto - singer
     * @return saved entity
     */
    @Transactional
    public SingerDTO saveSinger(@NotNull SingerDTO dto) {
        SingerEntity entity = singerRepository.findByName(dto.getName());
        if (entity == null) {
            entity = singerRepository.save(singerMapper.toEntity(dto));
        }

        return singerMapper.toDTO(entity);
    }


    public static class SingerSearcher extends StringSearcher<SingerEntity> {

        public SingerSearcher(StringSearcherDTO dto) {
            super(dto, SingerEntity_.name);
        }
    }
}
