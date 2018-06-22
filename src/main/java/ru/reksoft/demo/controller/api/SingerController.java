package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.SingerService;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * Returns created singer id and location.
     *
     * @param singerDTO - sent singer
     */
    @PostMapping("/create")
    public void createSinger(@RequestBody @Validated(SingerDTO.CreateCheck.class) SingerDTO singerDTO, HttpServletRequest request, HttpServletResponse response) {
        String id = singerService.createSinger(singerDTO).toString();

        response.setHeader("id", id);
        response.setHeader("location", ResourceLocationBuilder.build(request.getRequestURL(), id));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Returns singer by id with full information
     *
     * @param id - singer id
     * @return singer
     */
    @GetMapping("/{id}")
    public SingerDTO getSinger(@PathVariable int id) {
        return singerService.getSinger(id);
    }
}
