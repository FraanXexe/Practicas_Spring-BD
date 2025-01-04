package com.example.escuela.controller;


import com.example.escuela.model.materia;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.service.materiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta clase maneja solicitudes REST
@RequestMapping("/api/materias") // Define la ruta de busqueda
public class materiaController {

    @Autowired // Inyección del servicio
    private materiaService materiaService;

    // Endpoint para insertar una nueva materia
    @PostMapping // Maneja solicitudes POST
    public materia agregarMateria(@RequestBody @Valid MateriaRequest materia) {
        return materiaService.guardarMateria(materia); // Llama al servicio para guardar la materia
    }

    // Endpoint para consultar todas las materias
    @GetMapping // Maneja solicitudes GET
    public List<materia> listarMaterias() {
        return materiaService.obtenerTodasLasMaterias(); // Llama al servicio para obtener las materias
    }
}
