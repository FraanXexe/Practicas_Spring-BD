package com.example.escuela.service;

import com.example.escuela.mappers.MateriaMapper;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MateriaResponse;
import com.example.escuela.repository.MateriaRepository;

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
}
