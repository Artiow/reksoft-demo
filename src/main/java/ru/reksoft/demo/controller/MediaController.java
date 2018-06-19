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
     * Return list of media with base information for current filter
     *
     * @return page with media
     */
    @PostMapping("/byFilter")
    public PageDTO<MediaShortDTO> getMediaList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getMediaList(filter);
    }


    /**
     * Return list of media with pagination by one of attribute (album, label, singer) id
     *
     * @return page with media
     */
    @PostMapping("/byAttribute")
    public PageDTO<MediaShortDTO> getMediaListByAttribute(
            @RequestParam("attribute") MediaSearchType attributeType, @RequestParam("id") Integer attributeId,
            @RequestBody PageDividerDTO pageDivider
    ) {
        return mediaService.getMediaListByAttribute(attributeType, attributeId, pageDivider);
    }


    /**
     * Return media by id with full information
     *
     * @return media
     */
    @GetMapping("/{id}")
    public MediaDTO getMedia(@PathVariable int id) {
        return mediaService.getMedia(id);
    }
}
