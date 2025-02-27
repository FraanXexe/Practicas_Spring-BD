package com.example.escuela.service;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.mappers.AlumnoMapper;
import com.example.escuela.model.AlumnoEntity;
import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import com.example.escuela.repository.AlumnoRepository;
import com.example.escuela.utils.Utilerias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.time.LocalDate;
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

    // Metodo para insertar alumno
    private final AlumnoMapper alumnoMapper;
    public AlumnoResponse guardarAlumno(AlumnoRequest alumnoRequest) {
        LocalDate fechaNacimiento = Utilerias.parsearYValidarFecha(alumnoRequest.getFechaNacimiento());
        // Mapear y guardar el alumno
        AlumnoEntity alumnoEntity = alumnoMapper.toAlumnoEntity(alumnoRequest);
        alumnoEntity.setFechaNacimiento(fechaNacimiento);
        return alumnoMapper.toAlumnoResponse(alumnoRepository.save(alumnoEntity));
      //return alumnoMapper.toAlumnoResponse(
        // alumnoRepository.save(
        // alumnoMapper.toAlumnoEntity(alumnoRequest)
        // .setFechaNacimiento(fechaNacimiento))
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

    // Obtener alumnos por ID
    public AlumnoResponse obtenerAlumnoById(Long alumnoId) {
        return alumnoRepository.findById(alumnoId)
                .map(alumnoMapper::toAlumnoResponse)
                .orElseThrow(() -> new ExcPersonalizada(
                        MensajesError.ID_NOT_REGISTERED_MESSAGE_ALUMNO,
                        HttpStatus.NOT_FOUND));
    }

    // Obtener alumnos por pagina
    public Page<AlumnoEntity> obtenerAlumnosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alumnoRepository.findAll(pageable);
    }

}
