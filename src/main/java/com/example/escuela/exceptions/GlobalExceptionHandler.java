package com.example.escuela.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorRespuesta>> handleGeneralException(Exception e) {
        // Log del error en consola
        System.err.println("Error capturado: " + e.getMessage());

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                ("Error: El servicio no está disponible");

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Manejo de errores para el suo de caracteres
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorRespuesta> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        System.err.println("Error capturado: " + e.getMessage());

        ErrorRespuesta errorRes = new ErrorRespuesta("Error: No es válido el uso de símbolos o caracteres no numéricos en el ID");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorRes);
    }
}
