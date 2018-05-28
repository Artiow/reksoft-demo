package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.MediaDTO;
import ru.reksoft.demo.dto.MediaShortDTO;
import ru.reksoft.demo.service.MediaService;

import java.util.List;

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
    @GetMapping(value = "/list")
    public Page<MediaShortDTO> getMediaList() {
        return mediaService.getMediaList();
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
