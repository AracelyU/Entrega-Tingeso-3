package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {



        @Query("SELECT n.anio, n.semestre FROM Nota n WHERE n.rut =:rut")
        List<Integer> newAgeAcademy(@Param("rut") String rut);

}
