package com.example.escuela.model.request;

import jakarta.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AlumnoRequest {

    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 50 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String nombre;


    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 25 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String apellidoPaterno;

    @Size(min= 1, max = 25 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String apellidoMaterno;

    @NotNull(message = "No puede ir vacio")
    @Past(message = "No puede ser mayor al dia de hoy")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Pattern(regexp =  "^[0-9-]+$", message = "Debe escribirse se la siguiente forma yyyy-mm-dd")
    private Date fechaNacimiento;

    @NotNull(message = "No puede ir vacio")
    @Pattern(regexp = "^[MF]$", message = "Debe ser 'M' o 'F'")
    private String genero;

    @NotNull(message = "No puede estar vacío")
    @Email(message = "Debe tener un formato válido, ejemplo: usuario123@dominio.com")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Debe tener un fomato válido, ejemplo: usuario123@dominio.com")
    private String correoElectronico;

//----------------------------------------------------

    // Getters y Setters

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
