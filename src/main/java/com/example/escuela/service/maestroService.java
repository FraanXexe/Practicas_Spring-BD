package com.example.escuela.service;


import com.example.escuela.model.maestro;
import com.example.escuela.model.materia;
import com.example.escuela.repository.maestroRepository;
import com.example.escuela.repository.materiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class maestroService {

    @Autowired
    private final maestroRepository maestroRepository;

    @Autowired
    private final materiaRepository materiaRepository;

    // Constructor para inyectar el repositorio
    public maestroService(maestroRepository maestroRepository, com.example.escuela.repository.materiaRepository materiaRepository) {
        this.maestroRepository = maestroRepository;
        this.materiaRepository = materiaRepository;
    }

    // Metodo para insertar maestros
    public maestro guardarMaestro(maestro maestro) {
        return maestroRepository.save(maestro); // Guarda el maestro en la base de datos
    }

    // Metodo para seleccionar maestros
    public List<maestro> obtenerTodosLosMaestros() {
        return this.maestroRepository.findAll(); // Obtiene todos los maestros
    }

    // Metodo para seleccionar materias por ID de maestro
    public List<materia> obtenerMateriasPorMaestroId(Long maestroId) {
        return materiaRepository.findMateriasBymaestroId(maestroId);
    }



}
