package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.service.LabelService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${api-path.label}")
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
    public LabelDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return labelService.get(id);
    }

    /**
     * Returns created label id and location.
     *
     * @param labelDTO - sent label
     */
    @PostMapping
    public void create(@RequestBody @Validated(LabelDTO.CreateCheck.class) LabelDTO labelDTO, HttpServletRequest request, HttpServletResponse response)
            throws ResourceCannotCreateException {
        response.setHeader(HttpHeaders.LOCATION, ResourceLocationBuilder.build(request, labelService.create(labelDTO)));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Update label by id.
     *
     * @param id       - label id
     * @param labelDTO - label data
     */
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Validated(LabelDTO.UpdateCheck.class) LabelDTO labelDTO, HttpServletResponse response)
            throws ResourceNotFoundException, ResourceOptimisticLockException {
        labelService.update(id, labelDTO);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * Delete label by id.
     *
     * @param id - label id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, HttpServletResponse response)
            throws ResourceNotFoundException {
        labelService.delete(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
