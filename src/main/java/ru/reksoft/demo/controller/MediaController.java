package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


    @RequestMapping(value = "/list")
    public List<MediaShortDTO> getMedia() {
        return mediaService.getMedia();
    }

    @RequestMapping(value = "/id{id}")
    public MediaShortDTO getMedia(@PathVariable int id) {
        return mediaService.getMedia(id);
    }

}
