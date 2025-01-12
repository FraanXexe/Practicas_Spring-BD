package com.example.escuela.service;
import com.example.escuela.model.Alumno;
import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import com.example.escuela.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Service // Indica que esta clase es un servicio
public class AlumnoService {

    @Autowired
    private final AlumnoRepository alumnoRepository;

    // Constructor para inyectar el repositorio
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // Metodo para insertar alumnos
    public AlumnoResponse guardarAlumno(AlumnoRequest alumnoRequest) {

        Alumno alumnoEntity=new Alumno();
        alumnoEntity.setNombre(alumnoRequest.getNombre());
        alumnoEntity.setApellidoPaterno(alumnoRequest.getApellidoPaterno());
        alumnoEntity.setApellidoMaterno(alumnoRequest.getApellidoMaterno());
        alumnoEntity.setFechaNacimiento(alumnoRequest.getFechaNacimiento()); // Aquí ya es un Date válido.
        alumnoEntity.setGenero(alumnoRequest.getGenero());
        alumnoEntity.setCorreoElectronico(alumnoRequest.getCorreoElectronico());
        alumnoRepository.save(alumnoEntity);
        return new AlumnoResponse(  alumnoEntity.getIdAlumno(),
                                    alumnoEntity.getNombre(),
                                    alumnoEntity.getApellidoPaterno(),
                                    alumnoEntity.getApellidoMaterno(),
                                    alumnoEntity.getFechaNacimiento(),
                                    alumnoEntity.getGenero(),
                                    alumnoEntity.getCorreoElectronico());
    }

    // Metodo para seleccionar alumnos
    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll(); // Obtiene todos los alumnos
    }

    // Metodo para seleccionar Alumnos por ID
    public List<Alumno> obtenerAlumnoById(Long alumnoId) {
        return alumnoRepository.findAlumnosById(alumnoId);
    }

    public Page<Alumno> obtenerAlumnosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alumnoRepository.findAll(pageable);
    }

}
