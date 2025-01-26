package com.example.escuela.exceptions;

import com.example.escuela.constants.MensajesError;
import org.springframework.http.HttpStatus;

public class ExcPersonalizada extends RuntimeException{
    private HttpStatus code;

    public HttpStatus getCode() {
        return code;
    }

    public ExcPersonalizada() {
        super();
    }

    public ExcPersonalizada(String message) {     // Construcctor con argumentos
        super(message); // Llama al constructor de la clase padre (RuntimeException)
    }

    public ExcPersonalizada(String message, HttpStatus code) { // Construcctor con varios argumentos
        super(message); // Llama al constructor de la clase padre (RuntimeException)
        this.code=code;
    }
}


