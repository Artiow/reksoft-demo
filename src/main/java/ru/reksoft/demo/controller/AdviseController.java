package ru.reksoft.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
     * Error page mapping
     */
    @RequestMapping("/error")
    public String toErrorPage(HttpServletRequest request, ModelMap map) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String title = "500", description = "Internal Server Error!";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                title = "400";
                description = "Bad Request!";

            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                title = "401";
                description = "Unauthorized!";

            } else if (statusCode == HttpStatus.PAYMENT_REQUIRED.value()) {
                title = "402";
                description = "Payment Required!";

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                title = "403";
                description = "Forbidden!";

            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                title = "404";
                description = "Not Found!";
            }
        }

        map.addAttribute("title", title);
        map.addAttribute("description", title + ": " + description);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
