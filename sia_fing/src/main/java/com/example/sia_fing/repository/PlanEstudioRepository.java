package com.example.sia_fing.repository;

import com.example.sia_fing.entity.PlanEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanEstudioRepository extends JpaRepository<PlanEstudio, Integer> {


}
