package com.example.escuela.controller;


import com.example.escuela.model.MaestroEntity;
import com.example.escuela.model.MateriaEntity;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MaestroResponse;
import com.example.escuela.model.response.MateriaResponse;
import com.example.escuela.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Define que esta clase maneja solicitudes REST
@RequestMapping("/api/materias") // Define la ruta de busqueda
@CrossOrigin(origins = "*")
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
    public List<MateriaResponse> listarMaterias() {
        return materiaService.obtenerTodasLasMaterias(); // Llama al servicio para obtener las materias
    }

    // Endpoint para ver maestros por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponse> obtenerMateriaById(@PathVariable("id") Long materiaId) {
        MateriaResponse materiaResponse = materiaService.obtenerMateriaById(materiaId);
        return ResponseEntity.ok(materiaResponse);
    }

    // Endpoint para ver por pagina los alumnos
    @GetMapping("/pag/{page}")
    public ResponseEntity<Object> obtenerAlumnosPaginados(@PathVariable int page) {
        int size = 10;  // Tamaño fijo de la pag

        Page<MateriaEntity> maestros = materiaService.obtenerMateriasPaginados(page, size);

        if (page >= maestros.getTotalPages()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "La página solicitada no existe.");
            return ResponseEntity.status(404).body(response); // Respuesta 404 con el mensaje
        }
        return ResponseEntity.ok(maestros); // Si la página es válida, devuelve los alumnos
    }
}
