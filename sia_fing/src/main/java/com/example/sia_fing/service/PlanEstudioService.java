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

    @Autowired
    NotaService notaService;

    @Autowired
    PrerrequisitoService prerrequisitoService;

    public List<PlanEstudio> obtenerPlanEstudios(){
        return planEstudioRepository.findAll();
    }

    public void eliminarPlanEstudios(){ planEstudioRepository.deleteAll();}

    public PlanEstudio guardarPlanEstudio(Integer cod_carr, String cod_plan, Integer nivel, Integer cod_asig, String nom_asig){
        PlanEstudio p = new PlanEstudio();
        p.setCod_carr(cod_carr);
        p.setCod_plan(cod_plan);
        p.setNivel(nivel);
        p.setCod_asig(cod_asig);
        p.setNom_asig(nom_asig);
        planEstudioRepository.save(p);
        return p;
    }

    /*
    obtener el número de ramos posibles por nivel (para ver la cantidad max de ramos que puede tomar)
     */
    public Integer obtenerNroRamosNivel(Integer nivel){
        return planEstudioRepository.nroRamosNivel(nivel);
    }



    // obtener ramos por carrera  (para cuando toma un ramo)
    public List<PlanEstudio> obtenerRamosPorCarrera(Integer cod_carr){
        return planEstudioRepository.findPlanEstudioByCod_carr(cod_carr);
    }

    // obtener los ramos que puede tomar
    public List<PlanEstudio> obtenerRamosInscribir(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return null;
        }
        List<PlanEstudio> ramos = obtenerRamosPorCarrera(ep.getCod_carr());
        List<PlanEstudio> ramosInscribir = new ArrayList<>();

        for(PlanEstudio r : ramos){
            if(verificarPrerrequisitos(r.getCod_asig()) == 1){
                ramosInscribir.add(r);
            }
        }
        return ramosInscribir;
    }


    /*
 cuantos ramos tiene el estudiante según anio y semestre y estar al tanto de su situación de carrera
 verificar que curse al menos 3 asignaturas de su plan para permanecer en la carrera
 */
    public Integer numeroRamos(Integer anio, Integer semestre){  // semestre es 1 y año es 2024
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return -1; // no hay estudiante registrado
        }

        Integer nro_ramos = notaService.numeroRamos(anio, semestre, ep.getRut());  // puede ser 0 o la cantidad de ramos
        if(nro_ramos < 3){
            return 0; // en peligro de no permanecer en su carrera
        }

        Integer nro_ramos_max = obtenerNroRamosNivel(ep.getNivel());

        if(nro_ramos > nro_ramos_max){
            return 2; // alcanzó el limite de ramos por inscribir
        }

        return 1; // su situación es estable
    }


    /*
verificar que se cumplen los prerrequisitos de un ramo (para poder inscribir un ramo)
*/
    public Integer verificarPrerrequisitos(Integer cod_asig){
        // obtener los prerrequisitos de una carrera
        List<Integer> codigos_prerrequisitos = prerrequisitoService.obtenerPrerrequisitos(cod_asig);
        if(codigos_prerrequisitos.isEmpty()){
            return -2; // no se encontraron prerrequisitos para este ramo
        }

        for(Integer c : codigos_prerrequisitos){
            // verificar si la nota de cada prerrequisitos existe y es mayor a 4
            Float nota = notaService.obtenerNotaDeRamo(c);

            if(nota == null){
                //System.out.println("asignatura: " + c + " tiene una nota: " + nota);
                return -1; // no esta registrado a ese ramo
            }

            if(nota < 4){
                return 0; // no paso el ramo
            }
        }
        return 1; // cumple todos los requisitos
    }








}
