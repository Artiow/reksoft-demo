package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @return page with media
     */
    @PostMapping("/list/byAttribute")
    public PageDTO<MediaShortDTO> getList(
            @RequestParam("attribute") String attributeType, @RequestParam("id") Integer attributeId,
            @RequestBody PageDividerDTO pageDivider
    ) {
        return mediaService.getListByAttribute(MediaSearchType.fromValue(attributeType), attributeId, pageDivider);
    }

    /**
     * Returns media by id with full information.
     *
     * @param id - media id
     * @return media
     */
    @GetMapping("/{id}")
    public MediaDTO get(@PathVariable int id) throws ResourceNotFoundException {
        return mediaService.get(id);
    }

    /**
     * Returns created media id and location.
     *
     * @param mediaDTO - sent media
     */
    @PostMapping
    public void create(@RequestBody @Validated(MediaDTO.CreateCheck.class) MediaDTO mediaDTO, HttpServletRequest request, HttpServletResponse response)
            throws ResourceCannotCreateException {
        response.setHeader(HttpHeaders.LOCATION, ResourceLocationBuilder.build(request, mediaService.create(mediaDTO)));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Update media by id.
     *
     * @param id       - media id
     * @param mediaDTO - media data
     */
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody @Validated(MediaDTO.UpdateCheck.class) MediaDTO mediaDTO, HttpServletResponse response) throws
            ResourceNotFoundException,
            ResourceCannotUpdateException,
            ResourceOptimisticLockException {

        mediaService.update(id, mediaDTO);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * Delete media by id.
     *
     * @param id - media id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id, HttpServletResponse response)
            throws ResourceNotFoundException {
        mediaService.delete(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
