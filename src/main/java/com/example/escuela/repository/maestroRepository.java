package com.example.escuela.repository;


import com.example.escuela.model.maestro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta clase es un repositorio
public interface maestroRepository extends JpaRepository<maestro, Long> {
    //Hereda métodos básicos del jpa como save() y findAll()



}
