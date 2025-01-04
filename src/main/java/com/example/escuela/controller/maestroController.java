package com.example.escuela.controller;

import com.example.escuela.model.maestro;
import com.example.escuela.model.materia;
import com.example.escuela.service.maestroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/maestros") // Define la ruta de busqueda
public class maestroController {

    @Autowired // Inyección
    private maestroService maestroService;

    // Endpoint para insertar un nuevo maestro
    @PostMapping // Maneja solicitudes POST
    public maestro agregarMaestro(@RequestBody maestro maestro) {
        return maestroService.guardarMaestro(maestro); // Llama al servicio para guardar el maestro
    }

    // Endpoint para consultar todos los maestros
    @GetMapping // Maneja solicitudes GET
    public List<maestro> listarMaestros() {
        return maestroService.obtenerTodosLosMaestros(); // Llama al servicio para obtener los maestros
    }

    // Endpoint para consumir en Servicio externo
    @GetMapping("/materiasBymaestro/{id}")
    public ResponseEntity<?> getMateriasPorMaestroId(@PathVariable Long id) {
        List<materia> materias = maestroService.obtenerMateriasPorMaestroId(id);
            return ResponseEntity.ok(materias);
    }
}


