package com.example.escuela.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MateriaResponse {

    private Long idMateria;

    private String nombreMateria;

    private String descripcionMateria;

}
