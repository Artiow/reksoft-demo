package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.domain.CurrentBasketEntity;
import ru.reksoft.demo.domain.UserEntity;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.mapper.BasketMapper;
import ru.reksoft.demo.repository.CurrentBasketRepository;
import ru.reksoft.demo.repository.MediaRepository;
import ru.reksoft.demo.repository.UserRepository;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

@Service
public class BasketService {

    private MessagesConfig messages;

    private UserRepository userRepository;
    private MediaRepository mediaRepository;

    private CurrentBasketRepository currentBasketRepository;

    private BasketMapper basketMapper;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Autowired
    public void setCurrentBasketEntity(CurrentBasketRepository currentBasketRepository) {
        this.currentBasketRepository = currentBasketRepository;
    }

    @Autowired
    public void setBasketMapper(BasketMapper basketMapper) {
        this.basketMapper = basketMapper;
    }


    /**
     * Returns basket by user auth.
     *
     * @return basket
     * @throws AuthorizationRequiredException - if authorization is missing
     */
    @Transactional(readOnly = true)
    public BasketDTO get() throws AuthorizationRequiredException {
        return basketMapper.toBasket(getCurrentUserEntity().getBasket());
    }

    /**
     * Add media by media id in user basket by user auth.
     *
     * @param mediaId - media id
     * @throws AuthorizationRequiredException - if authorization is missing
     * @throws ResourceNotFoundException      - if media with id not found
     * @throws ResourceCannotCreateException  - if current basket already exist for user and media
     */
    @Transactional
    public void add(@NotNull Integer mediaId) throws AuthorizationRequiredException, ResourceNotFoundException, ResourceCannotCreateException {
        UserEntity user = getCurrentUserEntity();
        Integer userId = user.getId();

        if (currentBasketRepository.existsByPkUserIdAndPkMediaId(userId, mediaId)) {
            throw new ResourceCannotCreateException(messages.getAndFormat("reksoft.demo.Basket.alreadyExist.message", userId, mediaId));
        } else {
            try {
                CurrentBasketEntity newItem = new CurrentBasketEntity();
                newItem.setMedia(mediaRepository.getOne(mediaId));
                newItem.setUser(user);

                user.getBasket().add(newItem);
                userRepository.save(user);
            } catch (EntityNotFoundException e) {
                throw new ResourceNotFoundException(messages.getAndFormat("reksoft.demo.Media.notExistById.message", mediaId), e);
            }
        }
    }

    /**
     * Update media quantity by media id in user basket by user auth.
     *
     * @param mediaId  - media id
     * @param quantity - quantity
     * @throws AuthorizationRequiredException - if authorization is missing
     * @throws ResourceNotFoundException      - if media not found in basket
     * @throws ResourceCannotUpdateException  - ???
     */
    @Transactional
    public void update(@NotNull Integer mediaId, @NotNull Integer quantity) throws AuthorizationRequiredException, ResourceNotFoundException, ResourceCannotUpdateException {

    }

    /**
     * Remove media from the basket by media id in user basket by user auth.
     *
     * @param mediaId - media id
     * @throws AuthorizationRequiredException - if authorization is missing
     * @throws ResourceNotFoundException      - if media not found in basket
     */
    @Transactional
    public void remove(@NotNull Integer mediaId) throws AuthorizationRequiredException, ResourceNotFoundException {

    }


    /**
     * Returns authenticated user id.
     *
     * @return user id
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    private Integer getCurrentUserId() throws AuthorizationRequiredException {
        try {
            return ((IdentifiedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (ClassCastException e) {
            throw new AuthorizationRequiredException(messages.get("reksoft.demo.auth.filter.credentialsNotFound.message"), e);
        }
    }

    /**
     * Returns authenticated user.
     *
     * @return user entity
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    private UserEntity getCurrentUserEntity() throws AuthorizationRequiredException {
        return userRepository.getOne(getCurrentUserId());
    }
}
