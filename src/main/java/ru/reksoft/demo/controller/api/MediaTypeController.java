package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.MediaTypeDTO;
import ru.reksoft.demo.dto.pagination.PageDTO;
import ru.reksoft.demo.dto.pagination.PageDividerDTO;
import ru.reksoft.demo.service.MediaTypeService;

@RestController
@RequestMapping("${api-path.mediatype}")
public class MediaTypeController {

    private MediaTypeService mediaTypeService;

    @Autowired
    public void setMediaTypeService(MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }


    @PostMapping("/list")
    public PageDTO<MediaTypeDTO> getList(@RequestBody PageDividerDTO dividerDTO) {
        return mediaTypeService.getList(dividerDTO);
    }
}
