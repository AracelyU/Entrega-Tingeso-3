package com.example.sia_fing.service;

import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.repository.PlanEstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlanEstudioService {

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    @Autowired
    PlanEstudioRepository planEstudioRepository;

    public List<PlanEstudio> obtenerPlanEstudios(){
        return planEstudioRepository.findAll();
    }

    public void eliminarPlanEstudios(){ planEstudioRepository.deleteAll();}
    public void guardarPlanEstudio(Integer cod_carr, String cod_plan, Integer nivel, Integer cod_asig, String nom_asig){
        PlanEstudio p = new PlanEstudio();
        p.setCod_carr(cod_carr);
        p.setCod_plan(cod_plan);
        p.setNivel(nivel);
        p.setCod_asig(cod_asig);
        p.setNom_asig(nom_asig);
        planEstudioRepository.save(p);


    }

    /*
    obtener el número de ramos posibles por nivel
     */
    public Integer obtenerNroRamosNivel(Integer nivel){
        return planEstudioRepository.nroRamosNivel(nivel);
    }

    /*
        obtener los ramos del siguiente nivel de estudiante
     */
    public List<PlanEstudio> obtenerRamosPosibles(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        List<PlanEstudio> pe = new ArrayList<>();



        return pe;
    }





}
