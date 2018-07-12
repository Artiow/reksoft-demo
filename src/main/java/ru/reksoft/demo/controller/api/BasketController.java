package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.BasketDTO;
import ru.reksoft.demo.service.BasketService;
import ru.reksoft.demo.service.generic.AuthorizationRequiredException;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import javax.validation.constraints.Min;

import static ru.reksoft.demo.util.ResourceLocationBuilder.buildURI;

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
     * @throws AuthorizationRequiredException - if user not authorized
     */
    @GetMapping
    public BasketDTO get() throws AuthorizationRequiredException {
        return basketService.get();
    }

    /**
     * Add media by media id in basket by user by authentication.
     *
     * @param mediaId - media id
     * @return user's basket location
     * @throws AuthorizationRequiredException - if user not authorized
     * @throws ResourceNotFoundException      - if media not found
     * @throws ResourceCannotCreateException  - if media cannot be added
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestParam("added") @Min(value = 1) int mediaId)
            throws AuthorizationRequiredException, ResourceNotFoundException, ResourceCannotCreateException {
        basketService.add(mediaId);
        return ResponseEntity.created(buildURI()).build();
    }

    /**
     * Add media quantity by media id in basket by user by authentication.
     *
     * @param mediaId  - media id
     * @param quantity - quantity of media
     * @return no content
     * @throws AuthorizationRequiredException - if user not authorized
     * @throws ResourceNotFoundException      - if media not found in basket
     */
    @PutMapping
    public ResponseEntity<Void> update(@RequestParam("updated") @Min(value = 1) int mediaId, @RequestParam("quantity") @Min(value = 1) int quantity)
            throws AuthorizationRequiredException, ResourceNotFoundException {
        basketService.update(mediaId, quantity);
        return ResponseEntity.noContent().build();
    }

    /**
     * Remove media from the basket by media id in basket by user by authentication.
     *
     * @param mediaId - media id
     * @return no content
     * @throws AuthorizationRequiredException - if user not authorized
     * @throws ResourceNotFoundException      - if media not found in basket
     */
    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam("removed") @Min(value = 1) int mediaId)
            throws AuthorizationRequiredException, ResourceNotFoundException {
        basketService.remove(mediaId);
        return ResponseEntity.noContent().build();
    }
}
