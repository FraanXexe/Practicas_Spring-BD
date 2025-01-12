package com.example.escuela.constants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LogUtils {
    public static void logError(String message, Exception e) {
        // Registrar el error en el log
        log.error("Ocurrió un error: {}", message, e);
    }
}
