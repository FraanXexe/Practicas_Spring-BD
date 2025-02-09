package com.example.escuela.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
/*
    //Excepcion para CaracterI legal Sin Comillas
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<ErrorRespuesta>> CaracterIlegalSinComillas(HttpMessageNotReadableException e) {
        // Log en consola
        LogUtils.logError(e.getMessage(), e);

            ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.INVALID_REQUEST_BODY_MESSAGE);
            List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorList);
    }

 */

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

    // Excepcion para control de errores en el token
    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<List<ErrorRespuesta>> ExceptionErrorToken(Exception e) {
        // Log del error en consola
        LogUtils.logError(e.getMessage(), e);

        // Crear objeto para lanzar el error
        ErrorRespuesta errorRes = new ErrorRespuesta
                (MensajesError.TOKEN_INVALIDE);

        List<ErrorRespuesta> errorList = Collections.singletonList(errorRes);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorList);
    }
    // Para tokens malformados
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<List<ErrorRespuesta>> handleMalformedJwtException(MalformedJwtException e) {
        LogUtils.logError(e.getMessage(), e);
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.TOKEN_MALFORMED);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonList(errorRes));
    }

    // Para tokens expirados
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<List<ErrorRespuesta>> handleExpiredJwtException(ExpiredJwtException e) {
        LogUtils.logError(e.getMessage(), e);
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.TOKEN_EXPIRED);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonList(errorRes));
    }

    // Para tokens no soportados
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<List<ErrorRespuesta>> handleUnsupportedJwtException(UnsupportedJwtException e) {
        LogUtils.logError(e.getMessage(), e);
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.TOKEN_UNSUPPORTED);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonList(errorRes));
    }

    // Para tokens ilegales o vacíos
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<List<ErrorRespuesta>> handleIllegalArgumentException(IllegalArgumentException e) {
        LogUtils.logError(e.getMessage(), e);
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.TOKEN_ILLEGAL);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonList(errorRes));
    }

    // Token invalido
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<List<ErrorRespuesta>> handleJwtException(JwtException e) {
        LogUtils.logError(e.getMessage(), e);
        ErrorRespuesta errorRes = new ErrorRespuesta(MensajesError.TOKEN_INVALIDO);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonList(errorRes));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonParsingError(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();

        Throwable cause = ex.getCause();
        if (cause instanceof JsonMappingException) {
            JsonMappingException jsonEx = (JsonMappingException) cause;
            for (JsonMappingException.Reference ref : jsonEx.getPath()) {
                errors.put("error", "La propiedad '" + ref.getFieldName() + "' está mal escrita.");
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }

        errors.put("error", "Error en la solicitud. Verifica los campos enviados o el cuerpo del Request.");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<Map<String, String>> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex) {
        if (ex.getPath().isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("mensaje", "Propiedad desconocida"), HttpStatus.BAD_REQUEST);
        }

        String fieldName = ex.getPath().get(0).getFieldName();
        String errorMessage = fieldName + " no es la propiedad correcta";

        return new ResponseEntity<>(Collections.singletonMap("mensaje", errorMessage), HttpStatus.BAD_REQUEST);
    }

    // Excepcion personalizada
    @ExceptionHandler(ExcPersonalizada.class)
    public ResponseEntity<ErrorRespuesta> handleEntityNotFoundException(ExcPersonalizada ex) {
        // Crear un objeto de respuesta con el mensaje de la excepción
        ErrorRespuesta errorRes = new ErrorRespuesta(ex.getMessage());
        // Devolver la respuesta con el código de estado 404 (Not Found)
        return ResponseEntity
                .status(ex.getCode())
                .body(errorRes);
    }
}



