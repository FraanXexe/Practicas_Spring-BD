package com.example.escuela.controller;


import com.example.escuela.model.alumno;
import com.example.escuela.service.alumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta clase maneja solicitudes REST
@RequestMapping("/api/alumnos") // Define la ruta de busqueda
public class alumnoController {

    @Autowired // Inyección del servicio
    private alumnoService alumnoService;

    // Endpoint para insertar un nuevo alumno
    @PostMapping // Maneja solicitudes POST
    public alumno agregarAlumno(@RequestBody alumno alumno) {
        return alumnoService.guardarAlumno(alumno); // Llama al servicio para guardar el alumno
    }

    // Endpoint para consultar todos los alumnos
    @GetMapping // Maneja solicitudes GET
    public List<alumno> listarAlumnos() {
        return alumnoService.obtenerTodosLosAlumnos(); // Llama al servicio para obtener los alumnos
    }
}
