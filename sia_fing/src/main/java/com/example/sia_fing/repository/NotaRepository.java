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
        obtener la nota de un ramo
         */
        @Query("SELECT n FROM Nota n WHERE n.rut =:rut AND n.cod_asig =:cod_asig")
        Nota NotaByCod_asig(@Param("cod_asig") Integer cod_asig, @Param("rut") String rut);

        /*
        obtener la cantidad de ramos inscritos de un estudiante por año y semestre señalado
         */
        @Query("SELECT count(*) FROM Nota n WHERE n.rut=:rut AND n.anio=:anio AND n.semestre=:semestre")
        Integer numeroRamos(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("rut") String rut);


        // obtener los ramos inscritos en el anio y semestre de un estudiante
        @Query("SELECT n FROM Nota n WHERE n.rut=:rut AND n.anio=:anio AND n.semestre=:semestre")
        List<Nota> ramosInscritos(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("rut") String rut);


        // obtener los ramos dados y reprobados
        @Query("SELECT n FROM Nota n WHERE n.rut=:rut AND n.anio!=:anio AND n.semestre!=:semestre AND n.nota < 4")
        List<Nota> ramosReprobados(@Param("anio") Integer anio, @Param("semestre") Integer semestre, @Param("rut") String rut);


        // obtener los ramos dados y aprobados
        @Query("SELECT n FROM Nota n WHERE n.rut=:rut AND n.nota >= 4")
        List<Nota> ramosAprobados(@Param("rut") String rut);

        // obtener los ramos inscritos
        @Query("SELECT n FROM Nota n WHERE n.rut=:rut AND n.nota is null")
        List<Nota> ramosInscritosNull(@Param("rut") String rut);


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
