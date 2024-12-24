package com.example.escuela.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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
/*
    @ManyToMany(mappedBy = "materias")
    @JsonIgnore  // Ignora esta relación al serializar a JSON
    private List<maestro> maestros;

    public List<maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<maestro> maestros) {
        this.maestros = maestros;
    }
*/
//____________________________________________________________________________

    //getters
    public Long getId_materia() {
        return id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //setters
    public void setId_materia(Long id_materia) {
        this.id_materia = id_materia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
