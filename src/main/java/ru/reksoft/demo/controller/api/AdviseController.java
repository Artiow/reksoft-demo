package ru.reksoft.demo.controller.api;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.reksoft.demo.config.MessagesConfig;
import ru.reksoft.demo.dto.handling.ErrorDTO;
import ru.reksoft.demo.dto.handling.ErrorMapDTO;
import ru.reksoft.demo.service.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class AdviseController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(AdviseController.class);

    private final MessagesConfig messages;

    @Autowired
    public AdviseController(MessagesConfig messages) {
        this.messages = messages;
    }

    public MessagesConfig getMessages() {
        return messages;
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex) {
        return errorDTO(ex, "Unexpected Internal Server Error.");
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleJwtException(JwtException ex) {
        return errorDTO(ex, "JWT Read/Write Error.");
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleFileNotFoundException(FileNotFoundException ex) {
        return errorDTO(ex, "Requested File Not Found.");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMapDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        String message = messages.getAndFormat("reksoft.demo.handle.MethodArgumentNotValidException.message", bindingResult.getObjectName());
        Map<String, String> errors = new HashMap<>(allErrors.size());
        for (ObjectError error : allErrors) {
            errors.put(((DefaultMessageSourceResolvable) error.getArguments()[0]).getCodes()[0], error.getDefaultMessage());
        }

        return new ErrorMapDTO(warnUUID("Sent Argument Not Valid."), ex.getClass().getName(), message, errors);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return warnDTO(ex, "Sent HTTP Message Not Readable.");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleResourceNotFoundException(ResourceNotFoundException ex) {
        return warnDTO(ex, "Requested Resource Not Found.");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return warnDTO(ex, "Requested User Not Found.");
    }

    @ExceptionHandler(ResourceCannotCreateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleResourceCannotCreateException(ResourceCannotCreateException ex) {
        return warnDTO(ex, "Sent Resource Cannot Create.");
    }

    @ExceptionHandler(ResourceCannotUpdateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleResourceCannotUpdateException(ResourceCannotUpdateException ex) {
        return warnDTO(ex, "Sent Resource Cannot Update.");
    }

    @ExceptionHandler(ResourceOptimisticLockException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleResourceOptimisticLockException(ResourceOptimisticLockException ex) {
        return warnDTO(ex, "Sent Resource Data Already Has Been Changed.");
    }


    public ErrorDTO errorDTO(Throwable ex, String logMessage) {
        return new ErrorDTO(errorUUID(ex, logMessage), ex.getClass().getName(), ex.getMessage());
    }

    public UUID errorUUID(Throwable ex, String logMessage) {
        UUID uuid = UUID.randomUUID();
        logger.error(logMessage + " UUID: {}", uuid, ex);
        return uuid;
    }

    public ErrorDTO warnDTO(Throwable ex, String logMessage) {
        return new ErrorDTO(warnUUID(logMessage), ex.getClass().getName(), ex.getMessage());
    }

    public UUID warnUUID(String logMessage) {
        UUID uuid = UUID.randomUUID();
        logger.warn(logMessage + " UUID: {}", uuid);
        return uuid;
    }


    /**
     * 404 handling.
     *
     * @return error path
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
