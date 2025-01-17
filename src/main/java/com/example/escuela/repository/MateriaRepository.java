package com.example.escuela.repository;

import com.example.escuela.model.MateriaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta clase es un repositorio
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
    // Hereda métodos básicos como save() y findAll() de JpaRepository
}
