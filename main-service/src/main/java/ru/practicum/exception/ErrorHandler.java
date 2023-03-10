package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            ValidationException.class,
            MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError badRequestException(final RuntimeException e) {
        log.info(HttpStatus.BAD_REQUEST + " {}", e.getMessage());
        return ApiError.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.BAD_REQUEST)
                .reason("Incorrectly made request.")
                .message(e.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundException(final NotFoundException e) {
        log.info(HttpStatus.NOT_FOUND + " {}", e.getMessage());
        return ApiError.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.NOT_FOUND)
                .reason("The required object was not found.")
                .message(e.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler({DataIntegrityViolationException.class, AccessException.class,
            TransactionSystemException.class,
            ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError notValidateException(final RuntimeException e) {
        log.info(HttpStatus.CONFLICT + " {}", e.getMessage());
        return ApiError.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.CONFLICT)
                .reason("Integrity constraint has been violated.")
                .message(e.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError throwable(final Throwable e) {
        log.info(HttpStatus.INTERNAL_SERVER_ERROR + " {}", e.getMessage());
        return ApiError.builder()
                .errors(List.of(e.getClass().getName()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .reason("Internet server error.")
                .message(e.getLocalizedMessage())
                .build();
    }

}
