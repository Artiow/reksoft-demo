package ru.reksoft.demo.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.dto.handling.ErrorDTO;
import ru.reksoft.demo.dto.handling.ValidateErrorDTO;

import java.util.UUID;

@RestControllerAdvice
public class AdviseController {

    private static Logger logger = LoggerFactory.getLogger(AdviseController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex) {
        UUID errorUUID = UUID.randomUUID();
        logger.error("Unexpected Internal Server Error. UUID: {}", errorUUID, ex);

        return new ErrorDTO(
                errorUUID, ex.getClass().getName(),
                "Unexpected Internal Server Error. Please contact the administrator."
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        UUID errorUUID = UUID.randomUUID();
        logger.warn("Sent HTTP Message Not Readable. UUID: {}", errorUUID);

        return new ErrorDTO(errorUUID, ex.getClass().getName(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        UUID errorUUID = UUID.randomUUID();
        logger.warn("Sent Argument Not Valid. UUID: {}", errorUUID);

        BindingResult result = ex.getBindingResult();
        return new ValidateErrorDTO(errorUUID, ex.getClass().getName(), result.getObjectName(), result.getAllErrors());
    }
}
