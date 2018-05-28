package ru.reksoft.demo.controller;

import io.swagger.annotations.ApiResponses;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class AdviseController implements ErrorController {

    /**
     * Index page mapping
     */
    @GetMapping("/")
    public String toIndexPage() {
        return "index";
    }

    /**
     * Error pages mapping
     */
    @GetMapping("/error")
    public String toErrorPage() {
        return "error-404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
