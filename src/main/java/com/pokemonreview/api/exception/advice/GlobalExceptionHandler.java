package com.pokemonreview.api.exception.advice;

import com.pokemonreview.api.exception.MyResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MyResourceException.class)
    public ResponseEntity<com.pokemonreview.api.exceptions.ErrorObject> handleResourceNotFoundException(MyResourceException ex) {
        com.pokemonreview.api.exceptions.ErrorObject errorObject = new com.pokemonreview.api.exceptions.ErrorObject();
        errorObject.setStatusCode(ex.getStatusCode());
        errorObject.setMessage(ex.getMessage());

        log.error(ex.getMessage(), ex);

        return new ResponseEntity<com.pokemonreview.api.exceptions.ErrorObject>(errorObject, HttpStatusCode.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<com.pokemonreview.api.exceptions.ErrorObject> handleException(RuntimeException e) {
        com.pokemonreview.api.exceptions.ErrorObject errorObject = new com.pokemonreview.api.exceptions.ErrorObject();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(e.getMessage());

        log.error(e.getMessage(), e);

        return new ResponseEntity<com.pokemonreview.api.exceptions.ErrorObject>(errorObject, HttpStatusCode.valueOf(500));
    }

}
