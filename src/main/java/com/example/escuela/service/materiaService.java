package com.example.escuela.service;


import com.example.escuela.model.materia;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.repository.materiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class materiaService {

    private final materiaRepository materiaRepository;

    // Constructor para inyectar el repositorio
    public materiaService(materiaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    // Metodo para insertar materias
    public materia guardarMateria(MateriaRequest materiaRequest) {
        materia materiaEntity=new materia();
        materiaEntity.setNombre(materiaRequest.getNombre());
        materiaEntity.setDescripcion(materiaRequest.getDescripcion());
        return materiaRepository.save(materiaEntity); // Guarda la materia en la base de datos
    }

    // Metodo para seleccionar materias
    public List<materia> obtenerTodasLasMaterias() {
        return materiaRepository.findAll(); // Obtiene todas las materias
    }
}
