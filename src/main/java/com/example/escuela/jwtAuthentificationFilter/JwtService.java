package com.example.escuela.jwtAuthentificationFilter;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.example.escuela.model.UserEntity;
import com.example.escuela.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@RequiredArgsConstructor
public class JwtService {

    // Problema 1: Clave secreta hardcodeada (debería estar en variables de entorno)
    // Problema 2: La clave parece estar en hexadecimal pero se decodifica como Base64
    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private final UserRepository userRepository;

    // Genera un token JWT básico
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    // Metodo principal de generación de tokens
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        String newToken = Jwts.builder()
                .setClaims(extraClaims)  // Claims adicionales personalizados
                .setSubject(user.getUsername())  // Identificador del usuario
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))  // Expiración (24 minutos)
                .signWith(getKey(), SignatureAlgorithm.HS256)  // Firma HMAC-SHA256
                .compact();
        // Actualizamos el token en la base de datos
        UserEntity userEntity = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        userEntity.setToken(newToken);
        userRepository.save(userEntity);

        return newToken;
    }

    // Decodifica la clave secreta
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);  // Error: La clave no es Base64 válido
        return Keys.hmacShaKeyFor(keyBytes);  // Genera clave HMAC
    }

    // Obtiene el username desde el token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);  // Extrae el subject (username)
    }

    // Valida el token contra un usuario
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        // Verificar si el token coincide con el almacenado en la base de datos
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return (username.equals(userDetails.getUsername()) &&
                token.equals(user.getToken()) &&
                !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Metodo genérico para obtener cualquier claim
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Obtiene fecha de expiración
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    // Verifica si el token ha expirado
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}