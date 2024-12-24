package com.example.escuela.service;


import com.example.escuela.model.alumno;
import com.example.escuela.repository.alumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class alumnoService {

    private final alumnoRepository alumnoRepository;

    // Constructor para inyectar el repositorio
    public alumnoService(alumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // Metodo para insertar alumnos
    public alumno guardarAlumno(alumno alumno) {
        return alumnoRepository.save(alumno); // Guarda el alumno en la base de datos
    }

    // Metodo para seleccionar alumnos
    public List<alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll(); // Obtiene todos los alumnos
    }
}
