package ru.reksoft.demo.controller.api;

import javassist.NotFoundException;
import javassist.tools.reflect.CannotCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.service.MediaService;
import ru.reksoft.demo.util.MediaSearchType;
import ru.reksoft.demo.util.ResourceLocationBuilder;

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
    public PageDTO<MediaShortDTO> getList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getListByFilter(filter);
    }

    /**
     * Returns list of media with pagination by one of attribute (album, label, singer) id.
     *
     * @param attributeType - attribute search type (byAlbum, byLabel, bySinger)
     * @param attributeId - attribute id
     * @return page with media
     */
    @PostMapping("/byAttribute")
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
    public MediaDTO get(@PathVariable int id) throws NotFoundException {
        return mediaService.get(id);
    }


    /**
     * Returns created media id and location.
     *
     * @param mediaDTO - sent media
     */
    @PostMapping
    public void create(@RequestBody @Validated(MediaDTO.CreateCheck.class) MediaDTO mediaDTO, HttpServletRequest request, HttpServletResponse response)
            throws CannotCreateException
    {
        response.setHeader("location", ResourceLocationBuilder.build(request, mediaService.create(mediaDTO)));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
