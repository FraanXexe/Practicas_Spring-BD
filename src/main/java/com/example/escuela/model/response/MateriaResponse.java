package com.example.escuela.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MateriaResponse {

    private Long id;

    private String nombreMateria;

    private String descripcionMateria;

}
