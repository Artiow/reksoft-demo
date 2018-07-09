package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.service.BasketService;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${api-path.basket}")
public class BasketController {

    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }


    /**
     * Returns basket for user by authentication.
     *
     * @param userId - user id
     * @return basket
     * @throws ResourceNotFoundException - if user not found
     */
    @GetMapping("/get")
    public BasketDTO get(@PathVariable int userId) throws ResourceNotFoundException {
        return basketService.get();
    }

    /**
     * Add media by media id in basket.
     *
     * @param mediaId  - media id
     * @param response - http response
     * @throws ResourceCannotUpdateException - if media cannot be added
     */
    @PutMapping("/add/{mediaId}")
    public void add(@PathVariable int mediaId, HttpServletResponse response) throws ResourceCannotUpdateException {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
