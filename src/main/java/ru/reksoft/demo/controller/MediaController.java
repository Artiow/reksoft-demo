package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.service.MediaService;
import ru.reksoft.demo.util.MediaSearchType;

@RestController
@RequestMapping("api/media")
public class MediaController {

    private MediaService mediaService;

    @Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }


    /**
     * Return list of media with base information for current filter.
     *
     * @param filter - media filter
     * @return page with media
     */
    @PostMapping("/byFilter")
    public PageDTO<MediaShortDTO> getMediaList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getMediaList(filter);
    }


    /**
     * Return list of media with pagination by one of attribute (album, label, singer) id
     *
     * @param attributeType - attribute search type (byAlbum, byLabel, bySinger)
     * @param attributeId - attribute id
     * @return page with media
     */
    @PostMapping("/byAttribute") //todo: merge to byFilter?
    public PageDTO<MediaShortDTO> getMediaListByAttribute(
            @RequestParam("attribute") String attributeType, @RequestParam("id") Integer attributeId,
            @RequestBody PageDividerDTO pageDivider
    ) {
        return mediaService.getMediaListByAttribute(MediaSearchType.fromValue(attributeType), attributeId, pageDivider);
    }


    /**
     * Return saved media.
     *
     * @param dto - sent media
     * @return saved media
     */
    @PostMapping("/save")
    public MediaDTO saveMedia(@RequestBody MediaDTO dto) {
        return mediaService.saveMedia(dto);
    }


    /**
     * Return media by id with full information
     *
     * @param id - media id
     * @return media
     */
    @GetMapping("/{id}")
    public MediaDTO getMedia(@PathVariable int id) {
        return mediaService.getMedia(id);
    }
}
