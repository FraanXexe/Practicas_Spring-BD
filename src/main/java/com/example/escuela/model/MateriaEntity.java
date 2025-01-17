package com.example.escuela.model;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "materia") // Asocia esta clase con la tabla 'materia' en la base de datos
public class MateriaEntity {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    private Long idMateria;

    @Column(nullable = false, length = 100) // Define una columna obligatoria con longitud máxima de 100
    private String nombre;

    @Column // Define una columna opcional
    private String descripcion;
}
