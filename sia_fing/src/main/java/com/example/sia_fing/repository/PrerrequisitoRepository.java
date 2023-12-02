package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Prerrequisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerrequisitoRepository extends JpaRepository<Prerrequisito, Integer> {

    /*
    obtener los prerrequisitos de un ramo
     */
    @Query("SELECT p.cod_prerreq FROM Prerrequisito p WHERE p.cod_asig =:cod_asig")
    List<Integer> findPrerrequisitoByCod_carr(@Param("cod_asig") Integer cod_asig);



}
