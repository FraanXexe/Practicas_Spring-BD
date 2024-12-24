package com.example.escuela.service;

import com.example.escuela.model.maestro;
import com.example.escuela.repository.maestroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class maestroService {

    @Autowired
    private final maestroRepository maestroRepository;

    // Constructor para inyectar el repositorio
    public maestroService(maestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    // Metodo para insertar maestross
    public maestro guardarMaestro(maestro maestro) {
        return maestroRepository.save(maestro); // Guarda el maestro en la base de datos
    }

    // Metodo para seleccionar maestros
    public List<maestro> obtenerTodosLosMaestros() {
        return this.maestroRepository.findAll(); // Obtiene todos los maestros
    }

}
