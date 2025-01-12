package com.example.escuela.exceptions;

import lombok.extern.slf4j.Slf4j;
import com.example.escuela.constants.MensajesError;
import com.example.escuela.constants.LogUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Excepcion General
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorRespuesta>> handleGeneralException(Exception e) {
        // Log del error en consola
        LogUtils.logError(e.getMessage(), e);

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                (MensajesError.GENERAL_ERROR_MESSAGE);

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Excepcion para fallo con el servicio externo
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<List<ErrorRespuesta>> Error503(Exception e) {
        // Log del error en consola
        LogUtils.logError(e.getMessage(), e);

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                (MensajesError.EXTERNAL_SERVICE_ERROR_MESSAGE);

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    // Exception para URL vacia
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<List<ErrorRespuesta>> UrlVaciaOincompleata(Exception e) {
        // Log del error en consola
        LogUtils.logError(e.getMessage(), e);

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                (MensajesError.URL_NOT_FOUND_MESSAGE);

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }

    //Excepcion para CaracterI legal Sin Comillas
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<ErrorRespuesta>> CaracterIlegalSinComillas(HttpMessageNotReadableException e) {
        // Log en consola
        LogUtils.logError(e.getMessage(), e);
        // Verificar si el error es causado por un formato de fecha inválido
        if (e.getCause() != null) { //obtiene la causa original de una excepción, si existe, permitiendo rastrear el origen del error en excepciones anidadas
            ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.INVALID_DATE_FORMAT_MESSAGE);
            List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorList);
        }
        // Si no es un error de fecha, manejar el caso genérico
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.INVALID_REQUEST_BODY_MESSAGE);
        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorList);
    }

    // Excepcion personalizada para ID no registrado
    @ExceptionHandler(ExcPersonalizada.class)
    public ResponseEntity<ErrorRespuesta> handleEntityNotFoundException(ExcPersonalizada ex) {
        // Crear un objeto de respuesta con el mensaje de la excepción
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.ID_NOT_REGISTERED_MESSAGE);

        // Devolver la respuesta con el código de estado 404 (Not Found)
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorRes);
    }

    // Manejo de errores para caracteres
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorRespuesta> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        // Log en consola
        LogUtils.logError(e.getMessage(), e);

        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.INVALID_ID_MESSAGE);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorRes);
    }

    // Excepcion para capturar errores de escritura
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespuesta> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Log en consola
        LogUtils.logError(ex.getMessage(), ex);

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // Cadena de errores
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
