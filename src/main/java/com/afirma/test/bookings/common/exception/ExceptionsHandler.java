package com.afirma.test.bookings.common.exception;

import com.afirma.test.bookings.common.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO RoomNotFoundExceptionHandler(RoomNotFoundException exception){
        return ErrorDTO.builder()
                .errors(Arrays.asList(exception.getMessage()))
                .code(HttpStatus.NOT_FOUND.name()).build();
    }
    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO DomainExceptionHandler(DomainException exception){
        return ErrorDTO.builder()
                .errors(Arrays.asList(exception.getMessage()))
                .code(HttpStatus.INTERNAL_SERVER_ERROR.name()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorDTO error = ErrorDTO.builder()
                .errors(errors)
                .code(HttpStatus.BAD_REQUEST.toString())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request) {

        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        ErrorDTO error = ErrorDTO.builder()
                .errors(errors)
                .code(HttpStatus.BAD_REQUEST.toString())
                .build();

        return ResponseEntity.badRequest().body(error);
    }


}
