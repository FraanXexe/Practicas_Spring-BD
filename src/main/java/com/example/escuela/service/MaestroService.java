package com.example.escuela.service;
import com.example.escuela.model.Maestro;
import com.example.escuela.model.Materia;
import com.example.escuela.model.request.MaestroRequest;
import com.example.escuela.model.response.MaestroResponse;
import com.example.escuela.repository.MaestroRepository;
import com.example.escuela.exceptions.ExcPersonalizada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service // Indica que esta clase es un servicio
public class MaestroService {

    @Autowired
    private final MaestroRepository maestroRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICIO_BASE_URL = "http://localhost:8081/api/maestros/materias/";

    // Constructor para inyectar el repositorio
    public MaestroService(MaestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    // Metodo para insertar maestros
    public MaestroResponse guardarMaestro(MaestroRequest maestroRequest) {
        Maestro maestroEntity=new Maestro();
        maestroEntity.setNombre(maestroRequest.getNombre());
        maestroEntity.setApellidoPaterno(maestroRequest.getApellidoPaterno());
        maestroEntity.setApellidoMaterno(maestroRequest.getApellidoMaterno());
        maestroEntity.setEspecialidad(maestroRequest.getEspecialidad());
        maestroEntity.setFechaContratacion(maestroRequest.getFechaContratacion());
        maestroEntity.setCorreoElectronico(maestroRequest.getCorreoElectronico());
        maestroRepository.save(maestroEntity); // Guarda el maestro en la base de datos
        return new MaestroResponse( maestroEntity.getIdMaestro(),
                                    maestroEntity.getNombre(),
                                    maestroEntity.getApellidoPaterno(),
                                    maestroEntity.getApellidoMaterno(),
                                    maestroEntity.getEspecialidad(),
                                    maestroEntity.getFechaContratacion(),
                                    maestroEntity.getCorreoElectronico());
    }

    // Metodo para seleccionar maestros
    public List<Maestro> obtenerTodosLosMaestros() {
        return this.maestroRepository.findAll(); // Obtiene todos los maestros
    }

    // Metodo para obtener materias por maestro
    public List<Materia> obtenerMateriasPorMaestroId(Long id) {
        try {
            System.out.println("iniciandoCusumoDelServicioExterno");
            String url = SERVICIO_BASE_URL + id;
            ResponseEntity<List<Materia>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Materia>>() {
                    }
            );
            if (response.getBody() == null || response.getBody().isEmpty()) {
                System.out.println("Materias vacias");
                throw new ExcPersonalizada("No se encontro un maestro con el ID " + id + " proporcionado");
            }
            System.out.println("Materias: " + response.getBody());
            return response.getBody();
        } catch (ResourceAccessException ex){
            System.out.println("Se produjo la excepcion vacio");
            throw ex;
        } catch (Exception e){
            throw e;
        }
    }
}
