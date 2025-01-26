package com.example.escuela.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoResponse {

    private Long idAlumno;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String fechaNacimiento;

    private String genero;

    private String correoElectronico;
}
