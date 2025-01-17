package com.example.escuela.controller;

import com.example.escuela.model.AlumnoEntity;
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
@CrossOrigin(origins = "*")
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
    public List<AlumnoResponse> listarAlumnos() {
        return alumnoService.obtenerAllAlumnos(); // Llama al servicio para obtener los alumnos
    }
/*
version 1 que ya existia
    // Endpoint para obtener las Alumnos por su ID
    @GetMapping("/{id}")
    public ResponseEntity<List<AlumnoEntity>> obtenerAlumnoById(@PathVariable("id") Long alumnoId) throws InterruptedException {
        List<AlumnoEntity> alumnoEntities = alumnoService.obtenerAlumnoById(alumnoId);
        if (alumnoEntities.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 si no tiene materias
        }
        System.out.println("Alumno: " + alumnoEntities.toString());
        return ResponseEntity.ok(alumnoEntities); // Retorna 200 OK con las materias
    }

    Version 2 que ya no tie el if ni usa eltidades sino response
    @GetMapping("/{id}")
    public ResponseEntity<List<AlumnoResponse>> obtenerAlumnoById(@PathVariable("id") Long alumnoId) {
        List<AlumnoResponse> alumnoResponses = alumnoService.obtenerAlumnoById(alumnoId);
        return ResponseEntity.ok(alumnoResponses); // Retorna 200 OK con los alumnos
    }

 */
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponse> obtenerAlumnoById(@PathVariable("id") Long alumnoId) {
        AlumnoResponse alumnoResponse = alumnoService.obtenerAlumnoById(alumnoId);
        return ResponseEntity.ok(alumnoResponse); // Retorna 200 OK con el alumno
    }


    // Endpoint para ver por pagina los alumnos
    @GetMapping("/pag/{page}")
    public ResponseEntity<Object> obtenerAlumnosPaginados(@PathVariable int page) {
        int size = 10;  // Tamaño fijo de la pag

        Page<AlumnoEntity> alumnos = alumnoService.obtenerAlumnosPaginados(page, size);

        if (page >= alumnos.getTotalPages()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "La página solicitada no existe.");
            return ResponseEntity.status(404).body(response); // Respuesta 404 con el mensaje
        }
        return ResponseEntity.ok(alumnos); // Si la página es válida, devuelve los alumnos
    }
}
