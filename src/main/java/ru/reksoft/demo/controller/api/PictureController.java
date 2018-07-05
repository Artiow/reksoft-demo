package ru.reksoft.demo.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.reksoft.demo.service.PictureService;
import ru.reksoft.demo.service.generic.FileNotFoundException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("${api-path.picture}")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private PictureService pictureService;

    @Autowired
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }


    /**
     * Download picture by id.
     *
     * @param id      - picture id
     * @param request - http request
     * @return picture resource
     * @throws ResourceNotFoundException - if record not found in database
     * @throws FileNotFoundException     - iuf file not found on disk
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id, HttpServletRequest request)
            throws ResourceNotFoundException, FileNotFoundException {
        Resource resource = pictureService.get(id);
        MediaType contentType;

        try {
            contentType = MediaType.parseMediaType(request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()));
        } catch (IOException e) {
            contentType = MediaType.APPLICATION_JSON;
            logger.info("Could Not Determine Requested File Type.");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(contentType)
                .body(resource);
    }

    /**
     * Upload picture and returns new resource URI.
     *
     * @param picture - picture file
     * @param request - http request
     * @return picture URI
     * @throws ResourceCannotCreateException - if resource cannot create
     */
    @PostMapping
    public ResponseEntity<Void> upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request)
            throws ResourceCannotCreateException {
        return ResponseEntity.created(ResourceLocationBuilder.buildURI(request, pictureService.create(picture))).build();
    }

    /**
     * Delete picture.
     *
     * @param id - picture id
     * @return no content
     * @throws ResourceNotFoundException - if record not found in database
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id)
            throws ResourceNotFoundException {
        pictureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}