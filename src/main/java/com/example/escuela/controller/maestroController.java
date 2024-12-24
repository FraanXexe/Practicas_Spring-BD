package com.example.escuela.controller;


import com.example.escuela.model.maestro;
import com.example.escuela.model.materia;
import com.example.escuela.service.maestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Endpoint para obtener las materias de un maestro por su ID
    @GetMapping("/materias/{id}")
    public ResponseEntity<List<materia>> obtenerMateriasPorMaestroId(@PathVariable("id") Long maestroId) {
        List<materia> materias = maestroService.obtenerMateriasPorMaestroId(maestroId);
        if (materias.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si no tiene materias
        }
        return ResponseEntity.ok(materias); // Retorna 200 OK con las materias
    }


}
