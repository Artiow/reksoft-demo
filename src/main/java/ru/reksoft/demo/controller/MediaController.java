package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.*;
import ru.reksoft.demo.dto.pagination.filters.MediaFilterDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.service.MediaService;

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
    @PostMapping("/list")
    public PageDTO<MediaShortDTO> getMediaList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getMediaList(filter);
    }


    /**
     * Return list of media with pagination by singer by id
     *
     * @return page with media
     */
    @PostMapping("/bySinger/{id}")
    public PageDTO<MediaShortDTO> getMediaListBySinger(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListBySinger(id, pd);
    }

    /**
     * Return list of media with pagination by label by id
     *
     * @return page with media
     */
    @PostMapping("/byLabel/{id}")
    public PageDTO<MediaShortDTO> getMediaListByLabel(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByLabel(id, pd);
    }

    /**
     * Return list of media with pagination by album by id
     *
     * @return page with media
     */
    @PostMapping("/byAlbum/{id}")
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByAlbum(id, pd);
    }


    /**
     * Return list of media with pagination by genre by id
     *
     * @return page with media
     */
    @PostMapping("/byGenreId/{id}")
    public PageDTO<MediaShortDTO> getMediaListByGenre(@PathVariable Integer id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByGenre(id, pd);
    }

    /**
     * Return list of media with pagination by genre by code
     *
     * @return page with media
     */
    @PostMapping("/byGenreCode/{code}")
    public PageDTO<MediaShortDTO> getMediaListByGenre(@PathVariable String code, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByGenre(code, pd);
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
