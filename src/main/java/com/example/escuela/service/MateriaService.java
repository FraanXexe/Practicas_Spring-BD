package com.example.escuela.service;
import com.example.escuela.model.Materia;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MateriaResponse;
import com.example.escuela.repository.MateriaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un servicio
public class MateriaService {

    private final MateriaRepository materiaRepository;

    // Constructor para inyectar el repositorio
    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    // Metodo para insertar materias
    public MateriaResponse guardarMateria(MateriaRequest materiaRequest) {
        Materia materiaEntity=new Materia();
        materiaEntity.setNombre(materiaRequest.getNombre());
        materiaEntity.setDescripcion(materiaRequest.getDescripcion());
        materiaRepository.save(materiaEntity); // Guarda la materia en la base de datos
        return new MateriaResponse( materiaEntity.getIdMateria(),
                                    materiaEntity.getNombre(),
                                    materiaEntity.getDescripcion());
    }

    // Metodo para seleccionar materias
    public List<Materia> obtenerTodasLasMaterias() {
        return materiaRepository.findAll(); // Obtiene todas las materias
    }
}
