package com.example.escuela.repository;


import com.example.escuela.model.materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indica que esta clase es un repositorio
public interface materiaRepository extends JpaRepository<materia, Long> {
    // Hereda métodos básicos como save() y findAll() de JpaRepository

    // Consulta personalizada para obtener las materias de un maestro por su ID
    @NativeQuery("select materia.id_materia , materia.nombre , materia.descripcion from maestro, maestro_materia, materia where id_maestro = maestro_id and id_materia = materia_id and id_maestro=:maestroId")
    List<materia> findMateriasBymaestroId(@Param("maestroId") Long maestroId);

}
