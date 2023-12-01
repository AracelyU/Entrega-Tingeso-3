package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Integer> {



}
