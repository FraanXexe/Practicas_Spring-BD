package com.example.escuela.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Excepcion General
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorRespuesta>> handleGeneralException(Exception e) {
        // Log del error en consola
        System.err.println("Error capturado: " + e.getMessage());

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                ("A ocurrido una excepcion");

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Excepcion para fallo con el servicio externo
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<List<ErrorRespuesta>> Error503(Exception e) {
        // Log del error en consola
        System.err.println("Error capturado: " + e.getMessage());

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                ("Servicio externo no disponible");

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Exception para URL vacia
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<List<ErrorRespuesta>> UrlVaciaOincompleata(Exception e) {
        // Log del error en consola
        System.err.println("Error capturado: " + e.getMessage());

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                ("La URL no esta completa");

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Excepcion personalizada
    @ExceptionHandler(ExcPersonalizada.class)
    public ResponseEntity<ErrorRespuesta> handleEntityNotFoundException(ExcPersonalizada ex) {
        // Crear un objeto de respuesta con el mensaje de la excepción
        ErrorRespuesta errorRes = new ErrorRespuesta(ex.getMessage());

        // Devolver la respuesta con el código de estado 404 (Not Found)
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorRes);
    }

    // Manejo de errores para caracteres
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorRespuesta> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        System.err.println("Error capturado: " + e.getMessage());

        ErrorRespuesta errorRes = new ErrorRespuesta("Error: No es válido el uso de símbolos o caracteres no numéricos en el ID");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorRes);
    }



    // Excepcion para capturar errores de escritura
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String errores = fieldErrors.stream()
                .map(fieldError ->
                                    "El campo ("
                                 +   fieldError.getField() + ") "
                                 +  fieldError.getDefaultMessage())
                .collect(Collectors.joining(", ")); // se usa para convertir una lista o colección de elementos en una sola cadena

        // crear la respuesta con la cadena de errores concatenados
        ErrorRespuesta errorRes = new ErrorRespuesta(errores);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorRes);
    }
}
