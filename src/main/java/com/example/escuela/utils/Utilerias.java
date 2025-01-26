package com.example.escuela.utils;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilerias {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parsearYValidarFecha(String fecha) {
        LocalDate fechaParseada = LocalDate.parse(fecha, DATE_FORMATTER);

        if (fechaParseada.isAfter(LocalDate.now())) {
            throw new ExcPersonalizada(MensajesError.DATE_NOT_VALIDATE_TO_TODAY, HttpStatus.OK);
        }
        return fechaParseada;
    }
}
