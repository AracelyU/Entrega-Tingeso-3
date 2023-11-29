package com.example.sia_fing.repository;

import com.example.sia_fing.entity.Prerrequisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrerrequisitoRepository extends JpaRepository<Prerrequisito, Integer> {


}
