package com.example.escuela.controller;

import com.example.escuela.model.Alumno;
import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import com.example.escuela.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Define que esta clase maneja solicitudes REST
@RequestMapping("/api/alumnos") // Define la ruta de busqueda
public class AlumnoController {

    @Autowired // Inyección del servicio
    private AlumnoService alumnoService;

    // Endpoint para insertar un nuevo alumno
    @PostMapping // Maneja solicitudes POST
    public AlumnoResponse agregarAlumno(@RequestBody @Valid AlumnoRequest alumno) {
        return alumnoService.guardarAlumno(alumno); // Llama al servicio para guardar el alumno
    }

    // Endpoint para consultar todos los alumnos
    @GetMapping // Maneja solicitudes GET
    public List<Alumno> listarAlumnos() {
        return alumnoService.obtenerTodosLosAlumnos(); // Llama al servicio para obtener los alumnos
    }

    // Endpoint para obtener las materias de un maestro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Alumno>> obtenerAlumnoById(@PathVariable("id") Long alumnoId) throws InterruptedException {
        List<Alumno> alumnos = alumnoService.obtenerAlumnoById(alumnoId);
        if (alumnos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si no tiene materias
        }
        System.out.println("Alumno: " + alumnos.toString());
        return ResponseEntity.ok(alumnos); // Retorna 200 OK con las materias
    }

    // Endpoint para ver por pagina los alumnos
    @GetMapping("/pag/{page}")
    public ResponseEntity<Object> obtenerAlumnosPaginados(@PathVariable int page) {
        int size = 10;  // Tamaño fijo de la pag

        Page<Alumno> alumnos = alumnoService.obtenerAlumnosPaginados(page, size);

        if (page >= alumnos.getTotalPages()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "La página solicitada no existe.");
            return ResponseEntity.status(404).body(response); // Respuesta 404 con el mensaje
        }
        return ResponseEntity.ok(alumnos); // Si la página es válida, devuelve los alumnos
    }
}
