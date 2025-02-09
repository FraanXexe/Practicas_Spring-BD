package com.example.escuela.controller;

import com.example.escuela.model.MaestroEntity;
import com.example.escuela.model.MateriaEntity;
import com.example.escuela.model.request.MaestroRequest;
import com.example.escuela.model.response.MaestroResponse;
import com.example.escuela.service.MaestroService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/maestros") // Define la ruta de busqueda
@CrossOrigin(origins = "*")
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
    public List<MaestroResponse> listarMaestros() {
        return maestroService.obtenerTodosLosMaestros(); // Llama al servicio para obtener los maestros
    }

    // Endpoint para consumir en Servicio externo
    @GetMapping("/materiasBymaestro/{id}")
    public ResponseEntity<?> getMateriasPorMaestroId(@PathVariable Long id) {
        List<MateriaEntity> materiaEntities = maestroService.obtenerMateriasPorMaestroId(id);
            return ResponseEntity.ok(materiaEntities);
    }

    // Endpoint para ver maestros por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MaestroResponse> obtenerMaestroById(@PathVariable("id") Long maestroId) {
        MaestroResponse maestroResponse = maestroService.obtenerMaestroById(maestroId);
        return ResponseEntity.ok(maestroResponse); // Retorna 200 OK con el alumno
    }

    // Endpoint para ver por pagina los maestros
    @GetMapping("/pag/{page}")
    public ResponseEntity<Object> obtenerMaestrosPaginados(@PathVariable int page) {
        int size = 10;  // Tamaño fijo de la pag

        Page<MaestroEntity> maestros = maestroService.obtenerMaestrosPaginados(page, size);

        if (page >= maestros.getTotalPages()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "La página solicitada no existe.");
            return ResponseEntity.status(404).body(response); // Respuesta 404 con el mensaje
        }
        return ResponseEntity.ok(maestros); // Si la página es válida, devuelve los alumnos
    }
}


