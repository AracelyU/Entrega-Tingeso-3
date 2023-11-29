package com.example.sia_fing.service;

import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.repository.PlanEstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanEstudioService {

    @Autowired
    PlanEstudioRepository planEstudioRepository;

    public List<PlanEstudio> obtenerPlanEstudios(){
        return planEstudioRepository.findAll();
    }

    public void guardarPlanEstudio(String cod_carr, String cod_plan, int nivel, String cod_asig, String nom_asig){
        PlanEstudio p = new PlanEstudio();
        p.setCod_carr(cod_carr);
        p.setCod_plan(cod_plan);
        p.setNivel(nivel);
        p.setCod_asig(cod_asig);
        p.setNom_asig(nom_asig);
        planEstudioRepository.save(p);
    }


}
