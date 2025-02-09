package com.example.escuela.auth;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.model.UserEntity;
import com.example.escuela.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.escuela.jwtAuthentificationFilter.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    // Dependencias inyectadas - proporcionan las capacidades necesarias para el servicio
    private final UserRepository userRepository;    // Acceso a la base de datos de usuarios
    private final JwtService jwtService;            // Generación y manejo de tokens JWT
    private final PasswordEncoder passwordEncoder;  // Codificación/verificación de contraseñas (no usado directamente aquí)
    private final AuthenticationManager authenticationManager;  // Gestión del proceso de autenticación de Spring Security

    public AuthResponse login(LoginRequest request) {

        try {
            // Autenticación de credenciales
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),  // Nombre de usuario proporcionado
                            request.getPassword()   // Contraseña en texto plano proporcionada
                    )
            );

            // Obtención de detalles del usuario desde la base de datos
            UserEntity user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();

            // Verificar si hay un token anterior y eliminarlo (opcional, depende del flujo de tu aplicación)
            if (user.getToken() != null) {
                // Eliminar el token anterior
                user.setToken(null);
            }

            // Generación del token JWT para el usuario autenticado
            String token = jwtService.getToken(user);

            // Guardar el nuevo token en la base de datos
            user.setToken(token); // Asignar el nuevo token
            userRepository.save(user); // Actualizar el usuario con el nuevo token

            // Construcción y retorno de la respuesta de autenticación
            return AuthResponse.builder()
                    .token(token)  // Incluye el token generado
                    .build();
            } catch (BadCredentialsException e){
            throw new ExcPersonalizada(
                    MensajesError.USER_INVALID,
                    HttpStatus.OK);
        }
    }
}
