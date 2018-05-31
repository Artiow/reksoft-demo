package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaFilterDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.dto.PageDTO;
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
    @GetMapping(value = "/album{singerId}")
    public PageDTO<MediaShortDTO> getMediaListBySinger(@PathVariable int singerId) {
        return mediaService.getMediaListByAlbum(singerId);
    }

    /**
     * Return list of media by label by id
     *
     * @return page with media
     */
    @GetMapping(value = "/album{labelId}")
    public PageDTO<MediaShortDTO> getMediaListByLabel(@PathVariable int labelId) {
        return mediaService.getMediaListByAlbum(labelId);
    }

    /**
     * Return list of media by album by id
     *
     * @return page with media
     */
    @GetMapping(value = "/album{albumId}")
    public PageDTO<MediaShortDTO> getMediaListByAlbum(@PathVariable int albumId) {
        return mediaService.getMediaListByAlbum(albumId);
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
