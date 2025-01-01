package com.example.escuela.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorRespuesta>> handleGeneralException(Exception e) {
        // Log del error
        System.err.println("Error capturado: " + e.getMessage());

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                ("Error: El servicio no está disponible");

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }
}
