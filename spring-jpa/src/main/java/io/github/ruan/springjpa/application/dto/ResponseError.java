package io.github.ruan.springjpa.application.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseError(int statusCode, String message, List<FieldError> erros) {

    public static ResponseError responseDefault(String message) {
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ResponseError conflict(String message){
        return new ResponseError(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
