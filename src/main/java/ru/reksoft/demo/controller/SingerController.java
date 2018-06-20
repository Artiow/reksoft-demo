package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.SingerService;

@RestController
@RequestMapping("api/singer")
public class SingerController {

    private SingerService singerService;

    @Autowired
    public void setSingerService(SingerService singerService) {
        this.singerService = singerService;
    }


    /**
     * Return list of singers for current searcher.
     *
     * @param searcher - page divider with search string
     * @return page with singers
     */
    @PostMapping("/list")
    public PageDTO<SingerDTO> getSingerList(@RequestBody StringSearcherDTO searcher) {
        return singerService.getSingerList(searcher);
    }

    /**
     * Return saved singer.
     *
     * @param dto - sent singer
     * @return saved singer
     */
    @PostMapping("/save")
    public SingerDTO saveSinger(@RequestBody SingerDTO dto) {
        return singerService.saveSinger(dto);
    }
}
