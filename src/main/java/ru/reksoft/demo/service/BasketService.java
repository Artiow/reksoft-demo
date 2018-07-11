package ru.reksoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.repository.CurrentBasketRepository;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.security.userdetails.IdentifiedUserDetails;

import javax.validation.constraints.NotNull;

@Service
public class BasketService {

    private MessagesConfig messages;

    private CurrentBasketRepository currentBasketRepository;

    @Autowired
    public void setMessages(MessagesConfig messages) {
        this.messages = messages;
    }

    @Autowired
    public void setCurrentBasketRepository(CurrentBasketRepository currentBasketRepository) {
        this.currentBasketRepository = currentBasketRepository;
    }


    /**
     * Returns basket by user auth.
     *
     * @return basket
     * @throws AuthorizationRequiredException - if authorization is missing
     */
    @Transactional(readOnly = true)
    public BasketDTO get() throws AuthorizationRequiredException {
        getUserId();
        return null;
    }

    /**
     * Add media by media id in user basket by user auth.
     *
     * @param mediaId - media id
     * @throws AuthorizationRequiredException - if authorization is missing
     */
    @Transactional
    public Integer add(@NotNull Integer mediaId) throws AuthorizationRequiredException {
        return getUserId();
    }

    /**
     * Update media quantity by media id in user basket by user auth.
     *
     * @param mediaId  - media id
     * @param quantity - quantity
     * @throws AuthorizationRequiredException - if authorization is missing
     */
    @Transactional
    public void update(@NotNull Integer mediaId, @NotNull Integer quantity) throws AuthorizationRequiredException {
        getUserId();
    }

    /**
     * Remove media from the basket by media id in user basket by user auth.
     *
     * @param mediaId - media id
     * @throws AuthorizationRequiredException - if authorization is missing
     */
    @Transactional
    public void remove(@NotNull Integer mediaId) throws AuthorizationRequiredException {
        getUserId();
    }


    /**
     * Returns authenticated use id.
     *
     * @return user id
     * @throws AuthorizationRequiredException - if user is anonymous
     */
    private Integer getUserId() throws AuthorizationRequiredException {
        try {
            return ((IdentifiedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (ClassCastException e) {
            throw new AuthorizationRequiredException(messages.get("reksoft.demo.auth.filter.credentialsNotFound.message"), e);
        }
    }
}
