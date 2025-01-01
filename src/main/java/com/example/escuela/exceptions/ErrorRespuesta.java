package com.example.escuela.exceptions;

public class ErrorRespuesta {
    private String mensaje;

    public ErrorRespuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
