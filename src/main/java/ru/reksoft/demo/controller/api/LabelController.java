package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.LabelDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.service.LabelService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

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
     * @throws ResourceNotFoundException - if label not found
     */
    @GetMapping("/{id}")
    public LabelDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return labelService.get(id);
    }

    /**
     * Returns created label and location.
     *
     * @param labelDTO - sent label
     * @return new label location
     * @throws ResourceCannotCreateException - if label cannot created
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(LabelDTO.CreateCheck.class) LabelDTO labelDTO)
            throws ResourceCannotCreateException {
        return ResponseEntity.created(buildURI(labelService.create(labelDTO))).build();
    }

    /**
     * Update label by id.
     *
     * @param id       - label id
     * @param labelDTO - label data
     * @return no content
     * @throws ResourceNotFoundException       - if label not found
     * @throws ResourceOptimisticLockException - if label was already updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Validated(LabelDTO.UpdateCheck.class) LabelDTO labelDTO)
            throws ResourceNotFoundException, ResourceOptimisticLockException {
        labelService.update(id, labelDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete label by id.
     *
     * @param id - label id
     * @return no content
     * @throws ResourceNotFoundException - if label not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws ResourceNotFoundException {
        labelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
