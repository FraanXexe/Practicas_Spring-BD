package com.example.escuela.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MaestroResponse {

    private Long idMaestro;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String especialidad;

    private Date fechaContratacion;

    private String correoElectronico;
}
