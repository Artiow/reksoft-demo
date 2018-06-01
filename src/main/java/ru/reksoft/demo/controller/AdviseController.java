package ru.reksoft.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/error")
    public String toErrorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }

        return null;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
