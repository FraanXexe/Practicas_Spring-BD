package com.example.escuela.config;

import com.example.escuela.jwtAuthentificationFilter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;  // Filtro personalizado para JWT
    private final AuthenticationProvider authProvider;  // Proveedor de autenticación configurado previamente

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Configuración de CSRF
                .csrf(csrf -> csrf.disable())  // Deshabilita CSRF (común en APIs stateless)

                // Configuración de autorización de requests
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()  // Permite acceso público a rutas /auth
                                .anyRequest().authenticated()  // Todas las demás rutas requieren autenticación
                )

                // Configuración de manejo de sesiones
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Sin sesiones HTTP
                )

                // Registro del proveedor de autenticación
                .authenticationProvider(authProvider)  // Usa el AuthenticationProvider configurado

                // Integración del filtro JWT personalizado
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
