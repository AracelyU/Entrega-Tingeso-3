package com.example.sia_fing.controller;


import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.service.EstudiantePrincipalService;
import com.example.sia_fing.service.HorarioService;
import com.example.sia_fing.service.NotaService;
import com.example.sia_fing.service.PlanEstudioService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping("/ramo")
public class PlanEstudioController {

    @Autowired
    PlanEstudioService planEstudioService;

    @Autowired
    NotaService notaService;

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    @GetMapping("/getAll")   // mostrar todos los ramos
    public ResponseEntity<List<PlanEstudio>> obtenerRamos(){
        List<PlanEstudio> p = planEstudioService.obtenerPlanEstudios();
        if(p.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(p);
    }

    // mostrar ramos por carrera elegida, el cod_carr es del estudiante principal
    // esto para ingresar horario
    @GetMapping("/getRamos/{cod_carr}")
    public ResponseEntity<List<PlanEstudio>> obtenerRamosPorCarreraS(@PathVariable("cod_carr") Integer cod_carr){
        List<PlanEstudio> pe = planEstudioService.obtenerRamosPorCarrera(cod_carr);
        if(pe.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pe);
    }



    // mostrar ramos por carrera elegida, el cod_carr es del estudiante principal
    // esto para ingresar horario
    @GetMapping("/getRamos")
    public ResponseEntity<List<PlanEstudio>> obtenerRamosPorCarrera(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }

        List<PlanEstudio> pe = planEstudioService.obtenerRamosPorCarrera(ep.getCod_carr());
        if(pe.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pe);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<PlanEstudio>> eliminarRamos(){
        planEstudioService.eliminarPlanEstudios();
        List<PlanEstudio> p = planEstudioService.obtenerPlanEstudios();
        return ResponseEntity.ok(p);
    }


    // obtener los ramos que puede y le tocan tomar
    @GetMapping("/getRamosTomar")
    public ResponseEntity<List<PlanEstudio>> obtenerRamosQuePuedeTomar(){
        List<PlanEstudio> pe = planEstudioService.obtenerRamosInscribir();
        return ResponseEntity.ok(pe);
    }


    // obtener cupos de un ramo por seccion
    @GetMapping("/getCupos/{cod_asig}/{seccion}")
    public ResponseEntity<Integer> cuposDisponibles(@PathVariable("cod_asig") Integer cod_asig,
                                                    @PathVariable("seccion") String seccion){
        System.out.println("se busca obtener los cupos");
        Integer nroInscritos = 50 - notaService.nroInscritos(cod_asig, seccion );
        System.out.println("se obtuvieron: " + nroInscritos);
        return ResponseEntity.ok(nroInscritos);
    }

    // obtener nombre de la asignatura por cod_asig
    @GetMapping("/getNombre/{cod_asig}")
    public ResponseEntity<String> obtenerNombreAsig(@PathVariable("cod_asig") Integer cod_asig){
        String nombre = planEstudioService.nombreRamo(cod_asig);
        return ResponseEntity.ok(nombre);
    }

    // para inscribir un ramo
    // esto para el momento que hayas escogido sección
    @PostMapping("/inscribirRamo/{cod_asig}/{seccion}")
    public ResponseEntity<Nota> inscribirRamo(@PathVariable("cod_asig") Integer cod_asig,
                                                @PathVariable("seccion") String seccion){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }

        Nota n = planEstudioService.inscribirRamo(seccion, cod_asig, ep.getAnio(), ep.getSemestre());
        System.out.println("se ingreso el ramo correctamente");

        if(n == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(n);
    }

    /*
verificar si el ramo cumple todos los requisitos para tomarlo
 */
    @GetMapping("/verifityPrerrequisito/{cod_asig}")
    public ResponseEntity<Integer> verificarPrerrequisito(@PathVariable("cod_asig") Integer cod_asig){
        Integer res = planEstudioService.verificarPrerrequisitos(cod_asig);
        return ResponseEntity.ok(res);
    }


}
