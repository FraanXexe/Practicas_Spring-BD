package com.example.escuela.repository;
import com.example.escuela.model.AlumnoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indica que esta clase es un repositorio
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long> {
    // Hereda métodos básicos como save() y findAll() de JpaRepository

    //Buscar alumno por ID
    @Query(value = "select * from alumno where id_alumno = :alumnoId", nativeQuery = true)
    List<AlumnoEntity> findAlumnosById(@Param("alumnoId") Long alumnoId);
}
