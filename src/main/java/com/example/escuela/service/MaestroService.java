package com.example.escuela.service;
import com.example.escuela.mappers.MaestroMapper;
import com.example.escuela.model.MateriaEntity;
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

import java.util.List;

@Service // Indica que esta clase es un servicio
public class MaestroService {

    @Autowired
    private final MaestroRepository maestroRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICIO_BASE_URL = "http://localhost:8081/api/maestros/materias/";

    // Constructor para inyectar el repositorio
    public MaestroService(MaestroRepository maestroRepository, MaestroMapper maestroMapper) {
        this.maestroRepository = maestroRepository;
        this.maestroMapper = maestroMapper;
    }

    // Metodo para insertar maestros
    private final MaestroMapper maestroMapper;
    public MaestroResponse guardarMaestro(MaestroRequest maestroRequest) {
        return  maestroMapper.toMaestroResponse(maestroRepository.save(
                maestroMapper.toMaestroEntity(maestroRequest)));
    }

    // Metodo para seleccionar maestros
    public List<MaestroResponse> obtenerTodosLosMaestros() {
        return  maestroMapper.toMaaestroResponseList(maestroRepository.findAll());
    }


    // Metodo para obtener materias por maestro
    public List<MateriaEntity> obtenerMateriasPorMaestroId(Long id) {
        try {
            System.out.println("iniciandoCusumoDelServicioExterno");
            String url = SERVICIO_BASE_URL + id;
            ResponseEntity<List<MateriaEntity>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MateriaEntity>>() {
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
