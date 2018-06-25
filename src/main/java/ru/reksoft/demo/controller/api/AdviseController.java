package ru.reksoft.demo.controller.api;

import javassist.NotFoundException;
import javassist.tools.reflect.CannotCreateException;
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
        UUID uuid = UUID.randomUUID();
        logger.error("Unexpected Internal Server Error. UUID: {}", uuid, ex);
        return new ErrorDTO(uuid, ex.getClass().getName(), "Unexpected Internal Server Error. Please contact the administrator.");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return new ValidateErrorDTO(
                warnUUID("Sent Argument Not Valid"), ex.getClass().getName(),
                result.getObjectName(), result.getAllErrors()
        );
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(NotFoundException ex) {
        return warnDTO(ex, "Requested Resource Not Found");
    }

    @ExceptionHandler(CannotCreateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleCannotCreateException(CannotCreateException ex) {
        return warnDTO(ex, "Sent Resource Cannot Create");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return warnDTO(ex, "Sent HTTP Message Not Readable");
    }

    private ErrorDTO warnDTO(Throwable ex, String logMessage) {
        return new ErrorDTO(warnUUID(logMessage), ex.getClass().getName(), ex.getMessage());
    }

    private UUID warnUUID(String logMessage) {
        UUID uuid = UUID.randomUUID();
        logger.warn(logMessage + ". UUID: {}", uuid);
        return uuid;
    }
}
