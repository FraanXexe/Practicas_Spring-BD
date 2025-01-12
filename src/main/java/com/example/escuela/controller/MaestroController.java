package com.example.escuela.controller;

import com.example.escuela.model.Maestro;
import com.example.escuela.model.Materia;
import com.example.escuela.model.request.MaestroRequest;
import com.example.escuela.model.response.MaestroResponse;
import com.example.escuela.service.MaestroService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maestros") // Define la ruta de busqueda
public class MaestroController {

    @Autowired // Inyección
    private MaestroService maestroService;

    // Endpoint para insertar un nuevo maestro
    @PostMapping // Maneja solicitudes POST
    public MaestroResponse agregarMaestro(@RequestBody @Valid MaestroRequest maestro) {
        return maestroService.guardarMaestro(maestro); // Llama al servicio para guardar el maestro
    }

    // Endpoint para consultar todos los maestros
    @GetMapping // Maneja solicitudes GET
    public List<Maestro> listarMaestros() {
        return maestroService.obtenerTodosLosMaestros(); // Llama al servicio para obtener los maestros
    }

    // Endpoint para consumir en Servicio externo
    @GetMapping("/materiasBymaestro/{id}")
    public ResponseEntity<?> getMateriasPorMaestroId(@PathVariable Long id) {
        List<Materia> materias = maestroService.obtenerMateriasPorMaestroId(id);
            return ResponseEntity.ok(materias);
    }
}


