package ru.reksoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <h1>Главный  контроллер</h1>
 */
@Controller
@ControllerAdvice
public class AdviseController {

    @GetMapping(value = {"/", "/index"})
    public String toIndexPage() {
        return "index";
    }

    @GetMapping(value = "/404")
    public String to404() {
        return "404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex) {
        return "redirect:/404";
    }

}
