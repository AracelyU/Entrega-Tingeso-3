package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {


        /*
        obtener el número de niveles posible
         */
        @Query("SELECT COUNT(*) FROM Nota n WHERE n.rut =:rut")  // se tiene que revisar porque no siempre son 6
        Integer nivelEstudiante(@Param("rut") String rut);


        // obtener el nuevo semestre
        @Query("SELECT n.anio, n.semestre FROM Nota n WHERE n.rut =:rut AND n.anio =(select MAX(anio) from Nota) AND n.semestre = (select MAX(semestre) from Nota)")
        List<Integer> newAgeAcademy(@Param("rut") String rut);

        /*
        obtener la nota de un ramo
         */
        @Query("SELECT n.nota FROM Nota n WHERE n.rut =:rut AND n.cod_asig =:cod_asig AND n.anio =(select MAX(anio) from Nota) AND n.semestre = (select MAX(semestre) from Nota)")
        Float NotaByCod_asig(@Param("cod_asig") Integer cod_asig, @Param("rut") String rut);

        /*
        obtener la cantidad de ramos inscritos de un estudiante por año y semestre señalado
         */
        @Query("SELECT count(*) FROM Nota n WHERE n.rut=:rut AND n.anio=:anio AND n.semestre=:semestre")
        Integer numeroRamos(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("rut") String rut);


        /*
        obtener la cantidad de veces que dio el estudiante un ramo
         */
        @Query("SELECT COUNT(*) FROM Nota n WHERE n.rut =:rut AND n.cod_asig =:cod_asig")
        Integer nroVecesQueDioUnRamo(@Param("rut") String rut, @Param("cod_asig") Integer cod_asig);


        /*
        obtener los codigos de los ramos inscritos de un estudiante por año y semestre señalado

         */
        @Query("SELECT n.cod_asig FROM Nota n WHERE n.rut=:rut AND n.anio=:anio AND n.semestre=:semestre")
        List<Integer> RamosAnioSemestre(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("rut") String rut);


        // contar cuantos alumno hay por anio, semestre y seccion de una asignatura
        @Query("SELECT count(*) FROM Nota n WHERE n.seccion =:seccion AND n.anio=:anio AND n.semestre=:semestre AND n.cod_asig =:cod_asig")
        Integer nroInscritos(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("seccion") String seccion, @Param("cod_asig") Integer cod_asig);


}
