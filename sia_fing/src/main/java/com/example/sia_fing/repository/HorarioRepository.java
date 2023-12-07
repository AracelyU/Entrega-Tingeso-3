package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {


    // obtener horario de una asignatura de un estudiante
    @Query("SELECT h from Horario h WHERE h.cod_asig =:cod_asig")
    Horario horarioEstudiante(@Param("cod_asig") Integer cod_asig);

    // obtener lista de todos los horarios del estudiante





}
