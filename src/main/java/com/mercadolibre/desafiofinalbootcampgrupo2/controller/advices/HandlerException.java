package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices;

import com.mercadolibre.desafiofinalbootcampgrupo2.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class HandlerException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = RepositoryException.class)
    protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(value = SectionSpaceNotAvailableException.class)
    protected ResponseEntity<Object> handleSectionSpace(SectionSpaceNotAvailableException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = TrackingException.class)
    protected ResponseEntity<Object> handleTrackingException(TrackingException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = VehicleException.class)
    protected ResponseEntity<Object> handleVehicleException(VehicleException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = DontMatchesException.class)
    protected ResponseEntity<Object> handleRepresentativeDontMatches(DontMatchesException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = DateInvalidException.class)
    protected ResponseEntity<Object> handleDateInvalid(DateInvalidException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFound(DateInvalidException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(value = RepresentativeInvalidException.class)
    protected ResponseEntity<Object> handleRepresentativeInvalid(DateInvalidException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}