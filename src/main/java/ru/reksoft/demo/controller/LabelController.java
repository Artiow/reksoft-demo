package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.LabelService;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/label")
public class LabelController {

    private LabelService labelService;

    @Autowired
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }


    /**
     * Return list of labels for current searcher.
     *
     * @param searcher - page divider with search string
     * @return page with labels
     */
    @PostMapping("/list")
    public PageDTO<LabelDTO> getLabelList(@RequestBody StringSearcherDTO searcher) {
        return labelService.getLabelList(searcher);
    }

    /**
     * Returns created label id and location.
     *
     * @param dto - sent label
     */
    @PostMapping("/create")
    public void createLabel(@RequestBody LabelDTO dto, HttpServletRequest request, HttpServletResponse response) {
        String id = labelService.createLabel(dto).toString();

        response.setHeader("id", id);
        response.setHeader("location", ResourceLocationBuilder.build(request.getRequestURL(), id));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Returns label by id with full information
     *
     * @param id - label id
     * @return label
     */
    @GetMapping("/{id}")
    public LabelDTO getAlbum(@PathVariable int id) {
        return labelService.getLabel(id);
    }
}
