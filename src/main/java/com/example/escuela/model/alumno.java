package com.example.escuela.model;

// Importaciones necesarias
import jakarta.persistence.*;
import java.util.Date;

@Entity // Indica que esta clase es una entidad JPA
@Table(name = "alumno") // Asocia esta clase con la tabla 'alumno' en la base de datos
public class alumno {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valores automáticamente
    private Long id_alumno;

    @Column(nullable = false, length = 50) // Define una columna obligatoria con longitud máxima de 50
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido_paterno;

    @Column(nullable = false, length = 50)
    private String apellido_materno;

    @Column(nullable = false)
    private Date fecha_nacimiento;

    @Column(nullable = false, length = 1)
    private String genero;

    @Column(unique = true, length = 100)
    private String correo_electronico;

    //getters
    public Long getId_alumno() {
        return id_alumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    //setters
    public void setId_alumno(Long id_alumno) {
        this.id_alumno = id_alumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
