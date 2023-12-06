package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Integer> {

    /*
    obtener los ramos de una carrera
     */
    @Query("SELECT p FROM PlanEstudio p WHERE p.cod_carr =:cod_carr")
    List<PlanEstudio> findPlanEstudioByCod_carr(@Param("cod_carr") Integer cod_carr);


    /*
    obtener nro de ramos según el nivel
     */
    @Query("Select count(*) FROM PlanEstudio p WHERE p.nivel =:nivel")
    Integer nroRamosNivel(@Param("nivel") Integer nivel);

    // obtener ramo según cod_asig
    @Query("Select p.cupos FROM PlanEstudio p WHERE p.cod_asig =:cod_asig")
    Integer obtenerCupos(@Param("cod_asig") Integer cod_asig);




}
