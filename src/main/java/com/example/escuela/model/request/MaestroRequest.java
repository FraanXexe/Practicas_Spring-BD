package com.example.escuela.model.request;

import jakarta.validation.constraints.*;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MaestroRequest {
    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 50 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String nombre;

    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 25 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String apellidoPaterno;

    @Size(max = 25 ,message = "Debe tener maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z]*$", message = "Debe contener solo letras")
    private String apellidoMaterno;

    @NotNull(message = "No puede ir vacio")
    @Size(min= 1, max = 30 ,message = "Debe tener minimo {min} y maximo {max} datos numéricos")  // Solo funciona con el nombre
    @Pattern(regexp =  "^[a-zA-Z][a-zA-Z0-9 ]*$", message = "Debe empezar solo con letras y solo contener letras y/o números")
    private String especialidad;

    @NotNull(message = "No puede ir vacio")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Debe escribirse de la siguiente forma yyyy-MM-dd") // Para validar el formato
    private String fechaContratacion;

    @NotNull(message = "No puede estar vacío")
    @Email(message = "Debe tener un formato válido, ejemplo: usuario123@dominio.com")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Debe tener un fomato válido, ejemplo: usuario123@dominio.com")
    private String correoElectronico;
}
