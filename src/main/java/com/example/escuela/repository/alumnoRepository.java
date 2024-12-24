package com.example.escuela.repository;


import com.example.escuela.model.alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta clase es un repositorio
public interface alumnoRepository extends JpaRepository<alumno, Long> {
    // Hereda métodos básicos como save() y findAll() de JpaRepository
}
