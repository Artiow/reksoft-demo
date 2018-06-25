package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public PageDTO<LabelDTO> getList(@RequestBody StringSearcherDTO searcher) {
        return labelService.getList(searcher);
    }

    /**
     * Returns label by id with full information.
     *
     * @param id - label id
     * @return label
     */
    @GetMapping("/{id}")
    public LabelDTO get(@PathVariable int id) {
        return labelService.get(id);
    }

    /**
     * Returns created label id and location.
     *
     * @param labelDTO - sent label
     */
    @PostMapping("/create")
    public void create(@RequestBody @Validated(LabelDTO.CreateCheck.class) LabelDTO labelDTO, HttpServletRequest request, HttpServletResponse response) {
        String id = labelService.create(labelDTO).toString();

        response.setHeader("location", ResourceLocationBuilder.build(request.getRequestURI(), id));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
