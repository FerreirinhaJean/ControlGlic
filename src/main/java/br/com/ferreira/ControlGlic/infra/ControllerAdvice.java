package br.com.ferreira.ControlGlic.infra;

import br.com.ferreira.ControlGlic.dtos.exception.ExceptionErrorResponseDto;
import br.com.ferreira.ControlGlic.dtos.exception.ExceptionMessageNotReadable;
import br.com.ferreira.ControlGlic.entities.exceptions.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequestError(MethodArgumentNotValidException exception) {
        var exceptionBadRequestResponseDtos = exception.getFieldErrors().stream().map(ExceptionErrorResponseDto::new).toList();
        return ResponseEntity.badRequest().body(exceptionBadRequestResponseDtos);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleBadRequestError(HttpMessageNotReadableException exception) {
        var exceptionMessageNotReadable = new ExceptionMessageNotReadable(exception);
        return ResponseEntity.badRequest().body(exceptionMessageNotReadable);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException exception) {
        var exceptionMessageNotReadable = new ExceptionMessageNotReadable(exception);
        return ResponseEntity.badRequest().body(exceptionMessageNotReadable);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException exception) {
        var exceptionMessageNotReadable = new ExceptionMessageNotReadable("User was not found");
        return ResponseEntity.badRequest().body(exceptionMessageNotReadable);
    }

}
