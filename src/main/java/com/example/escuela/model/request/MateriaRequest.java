package com.example.escuela.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MateriaRequest {
    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 10 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z][a-zA-Z0-9 ]*$", message = "Debe contener solo letras")
    private String nombreMateria;

    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 60 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")
    private String descripcionMateria;
}
