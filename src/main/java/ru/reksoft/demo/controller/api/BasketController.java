package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.service.BasketService;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceCannotUpdateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;
import ru.reksoft.demo.util.ResourceLocationBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("${api-path.basket}")
public class BasketController {

    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }


    /**
     * Returns basket by user by authentication.
     *
     * @return basket
     */
    @GetMapping
    public BasketDTO get() throws AuthorizationRequiredException {
        return basketService.get();
    }

    /**
     * Add media by media id in basket by user by authentication.
     *
     * @param mediaId  - media id
     * @param request  - http request
     * @param response - http response
     * @throws ResourceCannotCreateException - if media cannot be added
     */
    @PostMapping
    public void add(@RequestParam("added") @Min(value = 1) int mediaId, HttpServletRequest request, HttpServletResponse response)
            throws AuthorizationRequiredException, ResourceCannotCreateException {
        basketService.add(mediaId);
        response.setHeader(HttpHeaders.LOCATION, ResourceLocationBuilder.build(request));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Add media quantity by media id in basket by user by authentication.
     *
     * @param mediaId  - media id
     * @param quantity - quantity of media
     * @param response - http response
     * @throws ResourceNotFoundException     - if media not found in basket
     * @throws ResourceCannotUpdateException - if media cannot be updated
     */
    @PutMapping
    public void update(@RequestParam("updated") @Min(value = 1) int mediaId, @RequestParam("quantity") @Min(value = 1) int quantity, HttpServletResponse response)
            throws AuthorizationRequiredException, ResourceNotFoundException, ResourceCannotUpdateException {
        basketService.update(mediaId, quantity);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * Remove media from the basket by media id in basket by user by authentication.
     *
     * @param mediaId  - media id
     * @param response - http response
     * @throws ResourceNotFoundException - if media not found in basket
     */
    @DeleteMapping
    public void remove(@RequestParam("removed") @Min(value = 1) int mediaId, HttpServletResponse response)
            throws AuthorizationRequiredException, ResourceNotFoundException {
        basketService.remove(mediaId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
