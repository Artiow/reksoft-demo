package ru.reksoft.demo.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.handling.ErrorDTO;
import ru.reksoft.demo.dto.handling.ValidateErrorDTO;

@RestControllerAdvice
public class AdviseController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex) {
        return new ErrorDTO(ex.getClass().getName(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ValidateErrorDTO(
                ex.getClass().getName(), "Validation failed for " + ex.getBindingResult().getObjectName(),
                ex.getBindingResult().getAllErrors()
        );
    }

}
