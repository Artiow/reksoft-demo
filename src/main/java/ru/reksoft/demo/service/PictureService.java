package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.PictureEntity;
import ru.reksoft.demo.repository.PictureRepository;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceFileNotFoundException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
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

    @Value("${file.pattern}")
    private String pattern;

    @Value("${file.location}")
    private String dir;

    private MessagesConfig messages;

    private Path fileStorageLocation;

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


    /**
     * Returns resource by id.
     *
     * @param id - resource id
     * @return resource
     * @throws ResourceNotFoundException     - if record not found in database
     * @throws ResourceFileNotFoundException - if file not found on disk
     */
    @Transactional(readOnly = true)
    public Resource get(@NotNull Integer id) throws ResourceNotFoundException, ResourceFileNotFoundException {
        try {
            Resource resource = new UrlResource((this.fileStorageLocation.resolve(getFilename(pictureRepository.getOne(id).getId()))).toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResourceFileNotFoundException(messages.getAndFormat("reksoft.demo.Picture.notExistByFile.message", id));
            }
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.notExistById.message", id), e);
        } catch (MalformedURLException e) {
            throw new ResourceFileNotFoundException(messages.getAndFormat("reksoft.demo.Picture.notExistByFile.message", id), e);
        }
    }

    /**
     * Returns new resource id.
     *
     * @param picture - resource
     * @return resource id
     * @throws ResourceCannotCreateException - if resource cannot create
     */
    @Transactional
    public Integer create(@NotNull MultipartFile picture) throws ResourceCannotCreateException {
        if (!picture.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
            throw new ResourceCannotCreateException(messages.get("reksoft.demo.Picture.couldNotStore.message"));
        } else {

            PictureEntity pictureEntity = new PictureEntity();

            pictureEntity.setSize(picture.getSize());
            pictureEntity.setUploaded(Timestamp.valueOf(LocalDateTime.now()));

            try {
                Integer newId = pictureRepository.save(pictureEntity).getId();
                Files.copy(picture.getInputStream(), this.fileStorageLocation.resolve(getFilename(newId)), StandardCopyOption.REPLACE_EXISTING);
                return newId;
            } catch (IOException e) {
                throw new ResourceCannotCreateException(messages.get("reksoft.demo.Picture.couldNotStore.message"), e);
            }
        }
    }

    /**
     * Delete resource by id.
     *
     * @param id - resource id
     * @throws ResourceNotFoundException - if record not found in database
     */
    @Transactional
    public void delete(@NotNull Integer id) throws ResourceNotFoundException {
        if (!pictureRepository.existsById(id)) {
            throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Picture.notExistById.message", id));
        } else {
            pictureRepository.deleteById(id);

            try {
                Files.delete(this.fileStorageLocation.resolve(getFilename(id)));
            } catch (IOException ignored) {
            }
        }
    }


    /**
     * Form picture filename.
     *
     * @param identifier - picture id
     * @return picture filename
     */
    private String getFilename(@NotNull Integer identifier) {
        return String.format(pattern, identifier);
    }
}
