package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.service.MediaService;
import ru.reksoft.demo.util.MediaSearchType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/media")
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
    @PostMapping("/byFilter")
    public PageDTO<MediaShortDTO> getMediaList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getMediaList(filter);
    }


    /**
     * Returns list of media with pagination by one of attribute (album, label, singer) id
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
     * Returns created media id and location.
     *
     * @param dto - sent media
     */
    @PostMapping("/create")
    public void createMedia(@RequestBody MediaDTO dto, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder builder = new StringBuilder(request.getRequestURL());

        String id = mediaService.createMedia(dto).toString();
        String location = builder.replace(builder.lastIndexOf("/") + 1, builder.length(), id).toString();

        response.setHeader("id", id);
        response.setHeader("location", location);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }


    /**
     * Returns media by id with full information
     *
     * @param id - media id
     * @return media
     */
    @GetMapping("/{id}")
    public MediaDTO getMedia(@PathVariable int id) {
        return mediaService.getMedia(id);
    }
}
