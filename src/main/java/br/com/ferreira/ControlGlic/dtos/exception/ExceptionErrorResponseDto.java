package br.com.ferreira.ControlGlic.dtos.exception;

import org.springframework.validation.FieldError;

public record ExceptionErrorResponseDto(
        String field,
        String message
) {
    public ExceptionErrorResponseDto(FieldError exception) {
        this(exception.getField(), exception.getDefaultMessage());
    }
}
