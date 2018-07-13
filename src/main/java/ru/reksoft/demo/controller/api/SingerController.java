package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.SingerDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.service.SingerService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

@RestController
@RequestMapping("${api-path.singer}")
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
    public PageDTO<SingerDTO> getList(@RequestBody StringSearcherDTO searcher) {
        return singerService.getList(searcher);
    }

    /**
     * Returns singer by id with full information.
     *
     * @param id - singer id
     * @return singer
     * @throws ResourceNotFoundException - if singer not found
     */
    @GetMapping("/{id}")
    public SingerDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return singerService.get(id);
    }

    /**
     * Returns created singer and location.
     *
     * @param singerDTO - sent singer
     * @return new singer location
     * @throws ResourceCannotCreateException - if singer cannot created
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(SingerDTO.CreateCheck.class) SingerDTO singerDTO)
            throws ResourceCannotCreateException {
        return ResponseEntity.created(buildURI(singerService.create(singerDTO))).build();
    }

    /**
     * Update singer by id.
     *
     * @param id        - singer id
     * @param singerDTO - singer data
     * @return no content
     * @throws ResourceNotFoundException       - if singer not found
     * @throws ResourceOptimisticLockException - if singer was already updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Validated(SingerDTO.UpdateCheck.class) SingerDTO singerDTO)
            throws ResourceNotFoundException, ResourceOptimisticLockException {
        singerService.update(id, singerDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete singer by id.
     *
     * @param id - singer id
     * @return no content
     * @throws ResourceNotFoundException - if singer not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws ResourceNotFoundException {
        singerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
