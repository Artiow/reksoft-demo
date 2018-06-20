package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.LabelService;

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
     * Return saved label.
     *
     * @param dto - sent label
     * @return saved label
     */
    @PostMapping("/create")
    public LabelDTO createLabel(@RequestBody LabelDTO dto) {
        return labelService.createLabel(dto);
    }
}
