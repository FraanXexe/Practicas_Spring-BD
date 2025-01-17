package com.example.escuela.service;
import com.example.escuela.mappers.AlumnoMapper;
import com.example.escuela.model.AlumnoEntity;
import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import com.example.escuela.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Service // Indica que esta clase es un servicio
public class AlumnoService {

    @Autowired
    private final AlumnoRepository alumnoRepository;

    // Constructor para inyectar el repositorio
    public AlumnoService(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    // Metodo para insertar alumnos
    private final AlumnoMapper alumnoMapper;
    public AlumnoResponse guardarAlumno(AlumnoRequest alumnoRequest) {
        return alumnoMapper.toAlumnoResponse(alumnoRepository.save(
               alumnoMapper.toAlumnoEntity(alumnoRequest)));
    }
/*
    // Obtener alumnos
    public List<AlumnoResponse> obtenerAllAlumnos(){
        List<Alumno> listaAlumno = alumnoRepository.findAll();  // Sale de BD
        List<AlumnoResponse> listaAlumnoResponse = listaAlumno.stream()
                .map(alumno -> {
                    // Aquí conviertes un objeto Alumno en AlumnoResponse
                    AlumnoResponse response = new AlumnoResponse();
                    response.setIdAlumno(alumno.getIdAlumno());
                    response.setNombre(alumno.getNombre());
                    response.setApellidoPaterno(alumno.getApellidoPaterno());
                    response.setApellidoMaterno(alumno.getApellidoMaterno());
                    response.setFechaNacimiento(alumno.getFechaNacimiento());
                    response.setGenero(alumno.getGenero());
                    response.setEmail(alumno.getCorreoElectronico());
                    return response;
                })
                .collect(Collectors.toList()); // Colectas los resultados en una lista
        return listaAlumnoResponse;
    }

 */
    // Obtener alumnos
    public List<AlumnoResponse> obtenerAllAlumnos() {
        return alumnoMapper.toAlumnoResponseList(alumnoRepository.findAll());
    }

/*
    // Metodo para seleccionar Alumnos por ID
    public List<AlumnoEntity> obtenerAlumnoById(Long alumnoId) {
        return alumnoRepository.findAlumnosById(alumnoId);
    }

    public List<AlumnoResponse> obtenerAlumnoById(Long alumnoId) {
        List<AlumnoEntity> alumnoEntities = alumnoRepository.findAlumnosById(alumnoId);
        return alumnoMapper.toAlumnoResponseList(alumnoEntities);
    }
 */
    public AlumnoResponse obtenerAlumnoById(Long alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoMapper::toAlumnoResponse)
                .orElse(null);
    }


    public Page<AlumnoEntity> obtenerAlumnosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alumnoRepository.findAll(pageable);
    }

}
