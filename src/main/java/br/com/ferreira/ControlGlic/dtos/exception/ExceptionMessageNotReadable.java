package br.com.ferreira.ControlGlic.dtos.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;

public record ExceptionMessageNotReadable(
        String message
) {
    public ExceptionMessageNotReadable(HttpMessageNotReadableException exception) {
        this(exception.getMessage());
    }
}
