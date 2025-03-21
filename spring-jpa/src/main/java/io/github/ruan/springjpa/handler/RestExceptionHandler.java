package io.github.ruan.springjpa.handler;

import io.github.ruan.springjpa.dto.FieldError;
import io.github.ruan.springjpa.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrorList = ex.getFieldErrors().
                stream().
                map(fieldError -> new FieldError(fieldError.getField(),
                fieldError.getDefaultMessage())).
                collect(Collectors.toList());

        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "fields in format invalid", fieldErrorList);
    }
}
