package com.example.escuela.model;

import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "materia") // Asocia esta clase con la tabla 'materia' en la base de datos
public class materia {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    private Long id_materia;

    @Column(nullable = false, length = 100) // Define una columna obligatoria con longitud máxima de 100
    private String nombre;

    @Column // Define una columna opcional
    private String descripcion;


//____________________________________________________________________________

    // Getters y Setters

    public Long getId_materia() {
        return id_materia;
    }

    public void setId_materia(Long id_materia) {
        this.id_materia = id_materia;
    }

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
