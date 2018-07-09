package ru.reksoft.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reksoft.demo.dto.BasketDTO;

import javax.validation.constraints.NotNull;

@Service
public class BasketService {

    /**
     * Returns basket for auth user.
     *
     * @return basket
     */
    @Transactional(readOnly = true)
    public BasketDTO get() {
        return null;
    }

    /**
     * Add media by media id in user's basket by user auth.
     *
     * @param mediaId - media id
     */
    @Transactional
    public void add(@NotNull Integer mediaId) {

    }
}
