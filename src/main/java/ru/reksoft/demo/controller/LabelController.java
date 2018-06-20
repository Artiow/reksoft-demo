package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.LabelService;
import ru.reksoft.demo.util.BindingResultErrorMessageBuilder;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

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
     * @param labelDTO - sent label
     */
    @PostMapping("/create")
    public void createLabel(@RequestBody @Validated(CreateCheck.class) LabelDTO labelDTO, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultErrorMessageBuilder.build(bindingResult.getObjectName(), bindingResult.getAllErrors()));
        }

        String id = labelService.createLabel(labelDTO).toString();

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
