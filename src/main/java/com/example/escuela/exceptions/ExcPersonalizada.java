package com.example.escuela.exceptions;

public class ExcPersonalizada extends RuntimeException{ // pertenece a la jerarquía de excepciones
    // contructor que recibira el parametro
    public ExcPersonalizada(String message) {
        super(message); // llama al constructor de la clase padre (RuntimeException)
    }
}
