package ru.reksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.AlbumShortDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/album")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }


    /**
     * Returns list of albums for current searcher.
     *
     * @param searcher - page divider with search string
     * @return page with albums
     */
    @PostMapping("/list")
    public PageDTO<AlbumShortDTO> getAlbumList(@RequestBody StringSearcherDTO searcher) {
        return albumService.getAlbumList(searcher);
    }

    /**
     * Returns created album id and location.
     *
     * @param dto - sent album
     */
    @PostMapping("/create")
    public void createAlbum(@RequestBody AlbumDTO dto, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder builder = new StringBuilder(request.getRequestURL());

        String id = albumService.createAlbum(dto).toString();
        String location = builder.replace(builder.lastIndexOf("/") + 1, builder.length(), id).toString();

        response.setHeader("id", id);
        response.setHeader("location", location);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Returns album by id with full information
     *
     * @param id - media id
     * @return media
     */
    @GetMapping("/{id}")
    public AlbumDTO getAlbum(@PathVariable int id) {
        return albumService.getAlbum(id);
    }
}
