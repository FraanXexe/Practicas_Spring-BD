package com.example.escuela.repository;


import com.example.escuela.model.MaestroEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta clase es un repositorio
public interface MaestroRepository extends JpaRepository<MaestroEntity, Long> {
    //Hereda métodos básicos del jpa como save() y findAll()



}
