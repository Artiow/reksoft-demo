package ru.reksoft.demo.controller.api;

import javassist.NotFoundException;
import javassist.tools.reflect.CannotCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.AlbumShortDTO;
import ru.reksoft.demo.dto.pagination.filters.StringSearcherDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.service.AlbumService;
import ru.reksoft.demo.util.ResourceLocationBuilder;

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
    public PageDTO<AlbumShortDTO> getList(@RequestBody StringSearcherDTO searcher) {
        return albumService.getList(searcher);
    }

    /**
     * Returns album by id with full information.
     *
     * @param id - album id
     * @return album
     */
    @GetMapping("/{id}")
    public AlbumDTO get(@PathVariable int id) throws NotFoundException {
        return albumService.get(id);
    }

    /**
     * Returns created album id and location.
     *
     * @param albumDTO - sent album
     */
    @PostMapping
    public void create(@RequestBody @Validated(AlbumDTO.FieldCheck.class) AlbumDTO albumDTO, HttpServletRequest request, HttpServletResponse response)
            throws CannotCreateException
    {
        response.setHeader("location", ResourceLocationBuilder.build(request, albumService.create(albumDTO)));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
