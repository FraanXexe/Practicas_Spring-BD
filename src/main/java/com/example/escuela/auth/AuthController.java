package com.example.escuela.auth;

import com.example.escuela.exceptions.ExcPersonalizada;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult)
    {
        // Log para revisar la peticion de el proyecto externo
        logger.info("Proyecto ESCUELA - Recibiendo petición de login desde: {}", request.getUsername());
        // Verificar si hay errores de validación
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));

            throw new ExcPersonalizada(errors, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(authService.login(request));
    }
}