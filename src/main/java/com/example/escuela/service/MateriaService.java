package com.example.escuela.service;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.mappers.MateriaMapper;
import com.example.escuela.model.MateriaEntity;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MateriaResponse;
import com.example.escuela.repository.MateriaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class MateriaService {

    private final MateriaRepository materiaRepository;

    // Constructor para inyectar el repositorio
    public MateriaService(MateriaRepository materiaRepository, MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }

    // Metodo para insertar materias
    private final MateriaMapper materiaMapper;
    public MateriaResponse guardarMateria(MateriaRequest materiaRequest) {
        return materiaMapper.toMateriaResponse(materiaRepository.save(
                materiaMapper.toMateriaEntity(materiaRequest)));
    }

    // Metodo para seleccionar materias
    public List<MateriaResponse> obtenerTodasLasMaterias() {
        return materiaMapper.toMateriaResponseList(materiaRepository.findAll());
    }

    // Obtener maestro por ID
    public MateriaResponse obtenerMateriaById(Long materiaId) {
        return materiaRepository.findById(materiaId)
                .map(materiaMapper::toMateriaResponse)
                .orElseThrow(() -> new ExcPersonalizada(
                        MensajesError.ID_NOT_REGISTERED_MESSAGE_MATERIA,
                        HttpStatus.OK));
    }

    // Obtener materias por pagina
    public Page<MateriaEntity> obtenerMateriasPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return materiaRepository.findAll(pageable);
    }
}
