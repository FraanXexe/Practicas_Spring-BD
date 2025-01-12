package com.example.escuela.controller;


import com.example.escuela.model.Materia;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MateriaResponse;
import com.example.escuela.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta clase maneja solicitudes REST
@RequestMapping("/api/materias") // Define la ruta de busqueda
public class MateriaController {

    @Autowired // Inyección del servicio
    private MateriaService materiaService;

    // Endpoint para insertar una nueva materia
    @PostMapping // Maneja solicitudes POST
    public MateriaResponse agregarMateria(@RequestBody @Valid MateriaRequest materia) {
        return materiaService.guardarMateria(materia); // Llama al servicio para guardar la materia
    }

    // Endpoint para consultar todas las materias
    @GetMapping // Maneja solicitudes GET
    public List<Materia> listarMaterias() {
        return materiaService.obtenerTodasLasMaterias(); // Llama al servicio para obtener las materias
    }
}
