package com.example.escuela.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "alumno") // Asocia esta clase con la tabla 'alumno' en la base de datos
public class alumno {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    private Long idAlumno;

    @Column(nullable = false, length = 50) // Define una columna obligatoria con longitud máxima de 50
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false, length = 1)
    private String genero;

    @Column(unique = true, length = 100)
    private String correoElectronico;

//-------------------------------------------------------------

    // Getters y Setters

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
