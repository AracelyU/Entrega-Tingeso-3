package com.example.sia_fing.controller;


import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.service.EstudiantePrincipalService;
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
    public ResponseEntity<List<PlanEstudio>> obtenerRamosPorCarrera(@PathVariable("cod_carr") Integer cod_carr){
        List<PlanEstudio> pe = planEstudioService.obtenerRamosPorCarrera(cod_carr);
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

    // enviar secciones disponibles de cursos
    // esto para el momento de inscribir un ramo eligas la sección
    @GetMapping("/getSecciones")
    public ResponseEntity<List<String>> obtenerSecciones(){
        List<String> lista = new ArrayList<>();
        lista.add("A-1");
        lista.add("B-2");
        lista.add("C-3");
        return ResponseEntity.ok(lista);
    }

    // obtener los ramos que puede y le tocan tomar
    @GetMapping("/getRamosTomar")
    public ResponseEntity<List<PlanEstudio>> obtenerRamosQuePuedeTomar(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        List<PlanEstudio> pe = planEstudioService.obtenerRamosInscribir();
        return ResponseEntity.ok(pe);
    }


    // para inscribir un ramo
    // esto para el momento que hayas escogido sección
    @PostMapping("/inscribirRamo/{cod_asig}/{seccion}/{anio}/{semestre}")
    public ResponseEntity<Nota> inscribirRamo(@PathVariable("cod_asig") Integer cod_asig,
                                                @PathVariable("seccion") String seccion,
                                                @PathVariable("anio") Integer anio,
                                                @PathVariable("semestre") Integer semestre){

        Nota n = planEstudioService.inscribirRamo(seccion, cod_asig, anio, semestre);
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
