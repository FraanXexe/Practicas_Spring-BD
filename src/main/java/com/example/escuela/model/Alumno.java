package com.example.escuela.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "alumno") // Asocia esta clase con la tabla 'alumno' en la base de datos
public class Alumno {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    private Long idAlumno;

    @Column(nullable = false, length = 50) // Define una columna obligatoria con longitud máxima de 50
    private String nombre;

    @Column(nullable = false, length = 25)
    private String apellidoPaterno;

    @Column(length = 25)
    private String apellidoMaterno;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false, length = 1)
    private String genero;

    @Column(length = 100)
    private String correoElectronico;
}
