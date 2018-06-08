package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.AlbumShortDTO;
import ru.reksoft.demo.dto.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.AlbumService;

@RestController
@RequestMapping("api/album")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }


    /**
     * Return list of albums for current searcher
     *
     * @return page with albums
     */
    @PostMapping("/list")
    public PageDTO<AlbumShortDTO> getAlbumList(@RequestBody StringSearcherDTO searcher) {
        return albumService.getAlbumList(searcher);
    }

    /**
     * Return saved album
     *
     * @return album
     */
    @PostMapping("/save")
    public AlbumDTO saveSinger(@RequestBody AlbumDTO dto) {
        return albumService.saveAlbum(dto);
    }
}
