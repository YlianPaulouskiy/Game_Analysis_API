package edu.bet.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.time.Instant;

@RestControllerAdvice
public class Handler {

//    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDetails handleEntityNotFound(EntityNotFoundException exception) {
//        LOGGER.warn(exception.getMessage());
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .httpStatus(HttpStatus.I_AM_A_TEAPOT)
                .details("Nothing found.")
                .code(HttpStatus.I_AM_A_TEAPOT.value())
                .build();
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDetails handleEntityExist(EntityExistsException exception) {
//        LOGGER.warn(exception.getMessage());
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .httpStatus(HttpStatus.I_AM_A_TEAPOT)
                .details("This data already exist in database.")
                .code(HttpStatus.I_AM_A_TEAPOT.value())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDetails handleEntityNotCorrect(RuntimeException exception) {
//        LOGGER.warn(exception.getMessage());
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .httpStatus(HttpStatus.I_AM_A_TEAPOT)
                .details("Incorrect input data.")
                .code(HttpStatus.I_AM_A_TEAPOT.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails incorrectInputDataDtoException(MethodArgumentNotValidException exception) {
//        LOGGER.warn(exception.getMessage());
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage().substring(exception.getMessage().lastIndexOf("[")))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .details("Incorrect Dto input data.")
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails incorrectInputDataException(ConstraintViolationException exception) {
//        LOGGER.warn(exception.getMessage());
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .details("Incorrect input data.")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
