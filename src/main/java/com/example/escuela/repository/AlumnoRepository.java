package com.example.escuela.repository;
import com.example.escuela.model.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indica que esta clase es un repositorio
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    // Hereda métodos básicos como save() y findAll() de JpaRepository

    //Buscar alumno por ID
    @Query(value = "select * from alumno where id_alumno = :alumnoId", nativeQuery = true)
    List<Alumno> findAlumnosById(@Param("alumnoId") Long alumnoId);
}
