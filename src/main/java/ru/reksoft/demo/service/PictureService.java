package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.dto.generic.DataTransferObject;
import ru.reksoft.demo.repository.PictureRepository;
import ru.reksoft.demo.service.generic.FileNotFoundException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Value("${file.location}")
    private String dir;

    private Path fileStorageLocation;

    private MessagesConfig messages;

    private PictureRepository pictureRepository;

    @PostConstruct
    public void setFileStorageLocation() {
        this.fileStorageLocation = Paths.get(dir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setPictureRepository(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    @Transactional(readOnly = true)
    public Resource get(Integer id) throws ResourceNotFoundException, FileNotFoundException {
        try {
            Resource resource = new UrlResource((this.fileStorageLocation.resolve(pictureRepository.getOne(id).getName()).normalize()).toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existByFile.message", id));
            }
        }  catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existById.message", id));
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existByFile.message", id), e);
        }
    }

    @Transactional
    public Integer create(DataTransferObject dataTransferObject) throws ResourceCannotCreateException {
        return null;
    }

    @Transactional
    public void update(Integer id, DataTransferObject dataTransferObject) throws ResourceNotFoundException {

    }

    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {

    }
}
