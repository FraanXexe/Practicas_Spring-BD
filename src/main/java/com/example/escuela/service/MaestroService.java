package com.example.escuela.service;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.mappers.MaestroMapper;
import com.example.escuela.mappers.MateriaMapper;
import com.example.escuela.model.MaestroEntity;
import com.example.escuela.model.MateriaEntity;
import com.example.escuela.model.request.MaestroRequest;
import com.example.escuela.model.response.MaestroResponse;
import com.example.escuela.repository.MaestroRepository;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.utils.Utilerias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service // Indica que esta clase es un servicio
public class MaestroService {

    @Autowired
    private MateriaMapper materiaMapper;  // Inyectamos el MateriaMapper

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

    /*
        // Metodo para insertar maestros
        private final MaestroMapper maestroMapper;
        public MaestroResponse guardarMaestro(MaestroRequest maestroRequest) {
            return  maestroMapper.toMaestroResponse(maestroRepository.save(
                    maestroMapper.toMaestroEntity(maestroRequest)));
    }
     */
    // Metodo para insertar maestros
    private final MaestroMapper maestroMapper;
    public MaestroResponse guardarMaestro(MaestroRequest maestroRequest) {
        LocalDate fechaContratacion = Utilerias.parsearYValidarFecha(maestroRequest.getFechaContratacion());
            // Mapear y guardar el maestro
        MaestroEntity maestroEntity = maestroMapper.toMaestroEntity(maestroRequest);
        maestroEntity.setFechaContratacion(fechaContratacion); // Asignar la fecha convertida
        return maestroMapper.toMaestroResponse(maestroRepository.save(maestroEntity));
      /*return maestroMapper.toMaestroResponse(
            maestroRepository.save(
                maestroMapper.toMaestroEntity(maestroRequest)
                    .setFechaContratacion(fechaContratacion)) */
    }

    // Metodo para seleccionar maestros
    public List<MaestroResponse> obtenerTodosLosMaestros () {
            return maestroMapper.toMaaestroResponseList(maestroRepository.findAll());
    }

    // Obtener maestro por ID
    public MaestroResponse obtenerMaestroById(Long maestroId) {
        return maestroRepository.findById(maestroId)
                .map(maestroMapper::toMaestroResponse)
                .orElseThrow(() -> new ExcPersonalizada(MensajesError.ID_NOT_REGISTERED_MESSAGE_ALUMNO,
                                       HttpStatus.OK));
    }

    // Obtener maestros por pagina
    public Page<MaestroEntity> obtenerMaestrosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return maestroRepository.findAll(pageable);
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
                throw new ExcPersonalizada(
                        MensajesError.ID_NOT_REGISTERED_MESSAGE_ALUMNO,
                        HttpStatus.OK);
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
