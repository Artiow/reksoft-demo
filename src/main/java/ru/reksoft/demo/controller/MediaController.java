package ru.reksoft.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.reksoft.demo.dto.MediaDTO;

@RestController
@RequestMapping("media")
public class MediaController {

    @GetMapping("/{id}")
    public MediaDTO getMedia(@PathVariable int id) {
        return null;
    }

}
