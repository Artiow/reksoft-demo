package ru.reksoft.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class AdviseController implements ErrorController {

    @GetMapping("/")
    public String toIndexPage() {
        return "index";
    }


    @RequestMapping("/error")
    public String toErrorPage() {
        return "error-404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
