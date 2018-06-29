package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.PictureEntity;
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
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existById.message", id));
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existByFile.message", id), e);
        }
    }

    @Transactional
    public Integer create(MultipartFile picture) throws ResourceCannotCreateException {
        String name = picture.getOriginalFilename();
        PictureEntity pictureEntity = new PictureEntity();

        if (pictureRepository.existsByName(name)) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Picture.existByName.message", name));
        }

        pictureEntity.setName(name);
        pictureEntity.setSize(picture.getSize());
        pictureEntity.setUploaded(Timestamp.valueOf(LocalDateTime.now()));

        try {
            Integer newId = pictureRepository.save(pictureEntity).getId();
            Files.copy(picture.getInputStream(), this.fileStorageLocation.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            return newId;
        } catch (IOException e) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Picture.couldNotStore.message", name), e);
        }
    }

    @Transactional
    public void update(Integer id, MultipartFile picture) throws ResourceNotFoundException, ResourceCannotCreateException {
        String name = picture.getOriginalFilename();
        PictureEntity pictureEntity;

        try {
            pictureEntity = pictureRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existById.message", id));
        }

        String oldName = pictureEntity.getName();

        pictureEntity.setName(name);
        pictureEntity.setSize(picture.getSize());
        pictureEntity.setUploaded(Timestamp.valueOf(LocalDateTime.now()));

        try {
            Files.copy(picture.getInputStream(), this.fileStorageLocation.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            pictureRepository.save(pictureEntity);
        } catch (IOException e) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Picture.couldNotStore.message", name), e);
        }

        if (!oldName.equals(pictureEntity.getName())) {
            try {
                Files.delete(this.fileStorageLocation.resolve(oldName));
            } catch (IOException ignored) {

            }
        }
    }

    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        String name;

        try {
            name = pictureRepository.getOne(id).getName();
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.existById.message", id));
        }

        try {
            Files.delete(this.fileStorageLocation.resolve(name));
        } catch (IOException ignored) {

        } finally {
            pictureRepository.deleteById(id);
        }
    }
}
