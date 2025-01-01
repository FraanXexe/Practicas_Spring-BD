package com.example.escuela.exceptions;

public class ErrorRespuesta {
    private String error;

    public ErrorRespuesta(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
