package com.example.escuela.service;

import com.example.escuela.model.maestro;
import com.example.escuela.model.materia;
import com.example.escuela.repository.maestroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class maestroService {

    @Autowired
    private final maestroRepository maestroRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICIO_BASE_URL = "http://localhost:8081/api/maestros/materias/";

    // Constructor para inyectar el repositorio
    public maestroService(maestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    // Metodo para insertar maestros
    public maestro guardarMaestro(maestro maestro) {
        return maestroRepository.save(maestro); // Guarda el maestro en la base de datos
    }

    // Metodo para seleccionar maestros
    public List<maestro> obtenerTodosLosMaestros() {
        return this.maestroRepository.findAll(); // Obtiene todos los maestros
    }

    // Metodo para obtener materias por maestro
    public List<materia> obtenerMateriasPorMaestroId(Long id) {
        String url = SERVICIO_BASE_URL + id;
            ResponseEntity<List<materia>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<materia>>() {
                    }
            );
            System.out.println("Materias: " + response.getBody());
            return response.getBody();

    }
}
