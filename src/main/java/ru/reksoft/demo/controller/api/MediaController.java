package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.shortcut.MediaShortDTO;
import ru.reksoft.demo.service.MediaService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.generic.ResourceOptimisticLockException;
import ru.reksoft.demo.util.MediaSearchType;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

@RestController
@RequestMapping("${api-path.media}")
public class MediaController {

    private MediaService mediaService;

    @Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }


    /**
     * Returns list of media with base information for current filter.
     *
     * @param filter - media filter
     * @return page with media
     */
    @PostMapping("/list/byFilter")
    public PageDTO<MediaShortDTO> getList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getListByFilter(filter);
    }

    /**
     * Returns list of media with pagination by one of attribute (album, label, singer) id.
     *
     * @param attributeType - attribute search type (byAlbum, byLabel, bySinger)
     * @param attributeId   - attribute id
     * @param pageDivider   - page divider
     * @return page with media
     */
    @PostMapping("/list/byAttribute")
    public PageDTO<MediaShortDTO> getList(
            @RequestParam("attribute") String attributeType,
            @RequestParam("id") Integer attributeId,
            @RequestBody PageDividerDTO pageDivider
    ) {
        return mediaService.getListByAttribute(MediaSearchType.fromValue(attributeType), attributeId, pageDivider);
    }

    /**
     * Returns media by id with full information.
     *
     * @param id - media id
     * @return media
     * @throws ResourceNotFoundException - if media not found
     */
    @GetMapping("/{id}")
    public MediaDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return mediaService.get(id);
    }

    /**
     * Returns created media id and location.
     *
     * @param mediaDTO - sent media
     * @return new media location
     * @throws ResourceCannotCreateException - if media cannot created
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated(MediaDTO.CreateCheck.class) MediaDTO mediaDTO)
            throws ResourceCannotCreateException {
        return ResponseEntity.created(buildURI(mediaService.create(mediaDTO))).build();
    }

    /**
     * Update media by id.
     *
     * @param id       - media id
     * @param mediaDTO - media data
     * @return no content
     * @throws ResourceNotFoundException       - if media not found
     * @throws ResourceCannotUpdateException   - if media cannot updated
     * @throws ResourceOptimisticLockException - if media was already updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody @Validated(MediaDTO.UpdateCheck.class) MediaDTO mediaDTO)
            throws ResourceNotFoundException, ResourceCannotUpdateException, ResourceOptimisticLockException {
        mediaService.update(id, mediaDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete media by id.
     *
     * @param id - media id
     * @return no content
     * @throws ResourceNotFoundException - of media not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
            throws ResourceNotFoundException {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
