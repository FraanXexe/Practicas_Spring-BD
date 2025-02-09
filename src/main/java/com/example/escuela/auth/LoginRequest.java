package com.example.escuela.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "El nombre de usuario es obligatorio")
    @NotBlank(message = "El nombre de usuario no puede estar en blanco")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre de usuario solo debe contener letras")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    String username;


    @NotEmpty(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 3, max = 30, message = "La contraseña debe tener entre 3 y 30 caracteres")
    String password;
}