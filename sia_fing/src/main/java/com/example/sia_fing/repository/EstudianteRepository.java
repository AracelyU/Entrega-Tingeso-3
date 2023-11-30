package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    // obtener estudiante por id
    @Query("SELECT e FROM Estudiante e WHERE e.id =:id")
    Estudiante findEstudianteById(@Param("id") int id);



}
