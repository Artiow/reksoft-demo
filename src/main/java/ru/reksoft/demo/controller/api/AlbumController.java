package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.shortcut.AlbumShortDTO;
import ru.reksoft.demo.service.AlbumService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

@RestController
@RequestMapping("${api-path.album}")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }


    /**
     * Returns list of albums for current searcher.
     *
     * @param searcher - page divider with search string
     * @return page with albums
     */
    @PostMapping("/list")
    public PageDTO<AlbumShortDTO> getList(@RequestBody StringSearcherDTO searcher) {
        return albumService.getList(searcher);
    }

    /**
     * Returns album by id with full information.
     *
     * @param id - album id
     * @return album
     * @throws ResourceNotFoundException - if album not found
     */
    @GetMapping("/{id}")
    public AlbumDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return albumService.get(id);
    }

    /**
     * Returns created album id and location.
     *
     * @param albumDTO - sent album
     * @return new album location
     * @throws ResourceCannotCreateException - if album cannot created
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(AlbumDTO.CreateCheck.class) AlbumDTO albumDTO)
            throws ResourceCannotCreateException {
        return ResponseEntity.created(buildURI(albumService.create(albumDTO))).build();
    }

    /**
     * Update album by id.
     *
     * @param id       - album id
     * @param albumDTO - album data
     * @return no content
     * @throws ResourceNotFoundException       - if album not found
     * @throws ResourceCannotUpdateException   - if album cannot updated
     * @throws ResourceOptimisticLockException - if album was already updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Validated(AlbumDTO.UpdateCheck.class) AlbumDTO albumDTO)
            throws ResourceNotFoundException, ResourceCannotUpdateException, ResourceOptimisticLockException {
        albumService.update(id, albumDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete album by id.
     *
     * @param id - album id
     * @return no content
     * @throws ResourceNotFoundException - if album not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws ResourceNotFoundException {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
