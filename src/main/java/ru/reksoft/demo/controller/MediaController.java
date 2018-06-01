package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.*;
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
     * Return total list of media
     *
     * @return page with media
     */
    @GetMapping(value = "/list")
    public PageDTO<MediaShortDTO> getMediaList() {
        return mediaService.getMediaList(new MediaFilterDTO());
    }

    /**
     * Return list of media with base information for current filter
     *
     * @return page with media
     */
    @PostMapping(value = "/list")
    public PageDTO<MediaShortDTO> getMediaList(@RequestBody MediaFilterDTO filter) {
        return mediaService.getMediaList(filter);
    }


    /**
     * Return list of media by singer by id
     *
     * @return page with media
     */
    @GetMapping(value = "/singer/{id}")
    public PageDTO<MediaShortDTO> getMediaListBySinger(@PathVariable int id) {
        return mediaService.getMediaListBySinger(id, new PageDividerDTO());
    }

    /**
     * Return list of media with pagination by singer by id
     *
     * @return page with media
     */
    @PostMapping(value = "/singer/{id}")
    public PageDTO<MediaShortDTO> getMediaListBySinger(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListBySinger(id, pd);
    }

    /**
     * Return list of media by label by id
     *
     * @return page with media
     */
    @GetMapping(value = "/label/{id}")
    public PageDTO<MediaShortDTO> getMediaListByLabel(@PathVariable int id) {
        return mediaService.getMediaListByLabel(id, new PageDividerDTO());
    }

    /**
     * Return list of media with pagination by label by id
     *
     * @return page with media
     */
    @PostMapping(value = "/label/{id}")
    public PageDTO<MediaShortDTO> getMediaListByLabel(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByLabel(id, pd);
    }

    /**
     * Return list of media by album by id
     *
     * @return page with media
     */
    @GetMapping(value = "/album/{id}")
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@PathVariable int id) {
        return mediaService.getMediaListByAlbum(id, new PageDividerDTO());
    }

    /**
     * Return list of media with pagination by album by id
     *
     * @return page with media
     */
    @PostMapping(value = "/album/{id}")
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@PathVariable int id, @RequestBody PageDividerDTO pd) {
        return mediaService.getMediaListByAlbum(id, pd);
    }


    /**
     * Return media by id with full information
     *
     * @return media
     */
    @GetMapping(value = "/{id}")
    public MediaDTO getMedia(@PathVariable int id) {
        return mediaService.getMedia(id);
    }
}
