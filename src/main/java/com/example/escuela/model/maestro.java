package com.example.escuela.model;

import jakarta.persistence.*;

import java.util.Date;

import lombok.*;

@Entity //Indica que esta clase es una entidad JPA
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "maestro") //Asocia esta clase con la tabla 'maestro'
@Getter
@Setter
public class maestro {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    @Column(name = "id_maestro")
    private Long idMaestro;

    @Column(name="nombre", nullable = false, length = 50) // Define una columna obligatoria con longitud máxima de 50
    private String nombre;

    @Column(name="apellido_paterno",nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno",nullable = false, length = 50)
    private String apellidoMaterno;

    @Column(name = "especialidad",length = 100) // Define una columna opcional con longitud máxima de 100
    private String especialidad;

    @Column(name = "fecha_contratacion", nullable = false) // Define una columna obligatoria
    private Date fechaContratacion;

    @Column(name = "correo_electronico", unique = true, length = 100) // Define una columna única con longitud máxima de 100
    private String correoElectronico;



//____________________________________________________________________________

// Getters and Setters

    public Long getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Long idMaestro) {
        this.idMaestro = idMaestro;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}
