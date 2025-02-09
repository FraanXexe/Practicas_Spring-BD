package com.example.escuela.jwtAuthentificationFilter;

import java.io.IOException;
import java.util.Optional;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.model.UserEntity;
import com.example.escuela.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /** Handler Exception Resolver. */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // Extraer el token del header Authorization
            final String token = getTokenFromRequest(request);
            final String username;

            // Si no hay token y la peticion viene de login, continua con la peticion
            if (token == null && request.getRequestURI().contains("/login")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Obtener username desde el token JWT
            username = jwtService.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);


                // Verificar que el token coincida con el almacenado en la base de datos
                if (userDetails != null) {
                    // obtener el usuario desde la base de datos usando el username
                    Optional<UserEntity> userOptional = userRepository.findByUsername(username);

                    if (userOptional.isPresent()) {
                        // si el usuario existe, lo obtenemos de Optional
                        UserEntity user = userOptional.get();

                        // Verificamos que el token enviado en la solicitud coincida con el almacenado
                        //  y que el token sea válido (no expirado, no manipulado, etc.)
                        if (token.equals(user.getToken()) && jwtService.isTokenValid(token, userDetails)) {
                            // Creamos un objeto de autenticación con el usuario autenticado y sus roles
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                            // Agregamos detalles de la solicitud a la autenticación (para logs o auditoría)
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            // Guardamos la autenticación en el contexto de seguridad de Spring
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        } else {
                            // Si el token es inválido o ha expirado, generamos un error y lo manejamos con una excepción personalizada
                            log.error("El token ha expirado o ha sido revocado");
                            this.resolver.resolveException(request, response, null,
                                    new ExcPersonalizada(
                                            MensajesError.TOKEN_REVOCADO,
                                            HttpStatus.OK));
                        }
                    } else {
                        // Si no encontramos el usuario en la base de datos, generamos un error y lo manejamos con una excepción personalizada
                        log.error("Usuario no encontrado en la base de datos");
                        this.resolver.resolveException(request, response, null,
                                new ExcPersonalizada(
                                        MensajesError.USER_INVALID,
                                        HttpStatus.OK));
                    }
                }
            }

            // Continuar con la cadena de filtros
            filterChain.doFilter(request, response);

        } catch (SignatureException e) {
            log.error("Error de firma en el token: {}", e.getMessage());
            this.resolver.resolveException(request, response, null, e);
        } catch (MalformedJwtException e) {
            log.error("Token malformado: {}", e.getMessage());
            this.resolver.resolveException(request, response, null, e);
        } catch (ExpiredJwtException e) {
            System.out.println("Expirado");
            log.error("Token expirado: {}", e.getMessage());
            this.resolver.resolveException(request, response, null, e);
        } catch (UnsupportedJwtException e) {
            log.error("Token no soportado: {}", e.getMessage());
            this.resolver.resolveException(request, response, null, e);
        } catch (IllegalArgumentException e) {
            log.error("Token ilegal o vacío: {}", e.getMessage());
            this.resolver.resolveException(request, response, null, e);
        }
    }

    // Metodo auxiliar para extraer el token del header Authorization
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Remueve el prefijo "Bearer "
        }
        return null;
    }
}


