package com.example.escuela.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MateriaRequest {
    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 10 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String nombre;

    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 60 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")
    private String descripcion;


//____________________________________________________________________________

    // Getters Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }







}
