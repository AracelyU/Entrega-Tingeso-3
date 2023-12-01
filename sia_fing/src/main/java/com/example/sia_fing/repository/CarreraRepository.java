package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Carrera;
import com.example.sia_fing.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    // obtener nombre de la carrera del estudiante
    @Query("SELECT c.nom_carr FROM Carrera c WHERE c.codigo_carr =:cod_carr")
    String findCarreraByCod_carr(@Param("cod_carr") Integer cod_carr);



}
