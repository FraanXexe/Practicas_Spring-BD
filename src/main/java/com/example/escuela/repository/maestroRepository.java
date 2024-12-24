package com.example.escuela.repository;


import com.example.escuela.model.maestro;
import com.example.escuela.model.materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Indica que esta clase es un repositorio
public interface maestroRepository extends JpaRepository<maestro, Long> {
    //Hereda métodos básicos del jpa como save() y findAll()



}