package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    // obtener el código de carrera del estudiante
    //@Query("SELECT e.cod_carr FROM Estudiante e WHERE e.rut =:rut")
    //Integer findCod_carrByRutEstudiante(@Param("rut") String rut);

    // obtener estudiante por su rut
    @Query("SELECT e FROM Estudiante e WHERE e.rut =:rut")
    Estudiante findEstudianteByRut(@Param("rut") String rut);

}
