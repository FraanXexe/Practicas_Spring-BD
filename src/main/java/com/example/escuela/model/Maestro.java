package com.example.escuela.model;

import jakarta.persistence.*;

import java.util.Date;

import lombok.*;

@Entity //Indica que esta clase es una entidad JPA
@Data
@Table(name = "maestro") //Asocia esta clase con la tabla 'maestro'
public class Maestro {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    @Column(name = "id_maestro")
    private Long idMaestro;

    @Column(nullable = false, length = 50) // Define una columna obligatoria con longitud máxima de 50
    private String nombre;

    @Column(nullable = false, length = 25)
    private String apellidoPaterno;

    @Column(length = 25)
    private String apellidoMaterno;

    @Column(length = 30) // Define una columna opcional con longitud máxima de 100
    private String especialidad;

    @Column(nullable = false) // Define una columna obligatoria
    private Date fechaContratacion;

    @Column(length = 100) // Define una columna única con longitud máxima de 100
    private String correoElectronico;
}
