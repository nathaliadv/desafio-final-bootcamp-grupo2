package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices;

import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerException {



        @ExceptionHandler(value = RepositoryException.class)
        protected ResponseEntity<Object> handlePersistencia(RepositoryException ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return ResponseEntity.badRequest().body(bodyOfResponse);
        }

}
