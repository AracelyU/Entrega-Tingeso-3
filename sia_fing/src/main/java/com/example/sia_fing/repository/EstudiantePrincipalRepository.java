package com.example.sia_fing.repository;

import com.example.sia_fing.entity.EstudiantePrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudiantePrincipalRepository extends JpaRepository<EstudiantePrincipal, Integer> {

    // el estudiante principal es el último registrado
    @Query("Select e FROM EstudiantePrincipal e WHERE e.id = (select max(id) from EstudiantePrincipal)")
    EstudiantePrincipal obtenerEstudiantePrincipal();



}
