package com.example.escuela.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaestroResponse {

    private Long idMaestro;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String especialidad;

    private Date fechaContratacion;

    private String correoElectronico;
}
