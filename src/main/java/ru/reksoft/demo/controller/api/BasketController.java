package ru.reksoft.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.service.BasketService;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("${api-path.basket}")
public class BasketController {

    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }


    @GetMapping("/for/{userId}")
    public void get(@PathVariable int userId) {

    }

    @PostMapping("/add/{mediaId}")
    public void add(@PathVariable int mediaId, HttpServletRequest request, HttpServletResponse response)
            throws ResourceCannotCreateException {
        response.setHeader(HttpHeaders.LOCATION, null);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
