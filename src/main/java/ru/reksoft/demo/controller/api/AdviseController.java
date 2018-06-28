package ru.reksoft.demo.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.dto.handling.ErrorDTO;
import ru.reksoft.demo.dto.handling.ErrorMapDTO;
import ru.reksoft.demo.service.generic.ResourceCannotCreateException;
import ru.reksoft.demo.service.generic.ResourceNotFoundException;

import java.util.*;

@RestControllerAdvice
public class AdviseController {

    private static final Logger logger = LoggerFactory.getLogger(AdviseController.class);

    private final MessagesConfig messages;

    @Autowired
    public AdviseController(MessagesConfig messages) {
        this.messages = messages;
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex) {
        UUID uuid = UUID.randomUUID();
        logger.error("Unexpected Internal Server Error. UUID: {}", uuid, ex);

        return new ErrorDTO(uuid, ex.getClass().getName(), messages.get("reksoft.demo.handle.Throwable.message"));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMapDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        String message = messages.getAndFormat("reksoft.demo.handle.MethodArgumentNotValidException.message", bindingResult.getObjectName());
        Map<String, String> errors = new HashMap<>(allErrors.size());
        for (ObjectError error: allErrors) {
            errors.put(((DefaultMessageSourceResolvable) error.getArguments()[0]).getCodes()[0], error.getDefaultMessage());
        }

        return new ErrorMapDTO(warnUUID("Sent Argument Not Valid"), ex.getClass().getName(), message, errors);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return warnDTO(ex, "Sent HTTP Message Not Readable");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(ResourceNotFoundException ex) {
        return warnDTO(ex, "Requested Resource Not Found");
    }

    @ExceptionHandler(ResourceCannotCreateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleCannotCreateException(ResourceCannotCreateException ex) {
        return warnDTO(ex, "Sent Resource Cannot Create");
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
