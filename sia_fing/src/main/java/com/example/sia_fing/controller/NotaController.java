package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.service.EstudiantePrincipalService;
import com.example.sia_fing.service.EstudianteService;
import com.example.sia_fing.service.NotaService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Nota>> obtenerNotas() {
        List<Nota> n = notaService.obtenerNotas();
        if (n.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(n);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<Nota>> eliminarNotas() {
        notaService.eliminarNotas();
        List<Nota> n = notaService.obtenerNotas();
        return ResponseEntity.ok(n);
    }

    /*
    consultar cuantas asignaturas tiene el alumno el año 2024 semestre 1
     */
    @GetMapping("/nroRamosInscritos")
    public ResponseEntity<Integer> nroRamosInscritos(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        Integer datos = notaService.numeroRamos(ep.getAnio(), ep.getSemestre(), ep.getRut());
        return ResponseEntity.ok(datos);
    }


    @GetMapping("/ramosInscritos")
    public ResponseEntity<List<Nota>> ramosInscrito(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        List<Nota> inscritos = notaService.ramosInscritos(ep.getRut(), ep.getAnio(), ep.getSemestre());
        if(inscritos == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inscritos);

    }

    @GetMapping("/ramosReprobados")
    public ResponseEntity<List<Nota>> ramosReprobados(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        List<Nota> inscritos = notaService.ramosReprobados(ep.getRut(), ep.getAnio(), ep.getSemestre());
        if(inscritos == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inscritos);

    }

    @GetMapping("/ramosAprobados")
    public ResponseEntity<List<Nota>> ramosAprobados(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        List<Nota> inscritos = notaService.ramosAprobados(ep.getRut(), ep.getAnio(), ep.getSemestre());
        if(inscritos == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inscritos);

    }

    /*
    ver el estado de repitencia del estudiante principal
     */
    @GetMapping("estadoRepitencia/{cod_asig}")
    public ResponseEntity<Integer> estadoRepitencia(@PathVariable("cod_asig") Integer cod_asig){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        Integer datos = notaService.situacionRepitenciaEstudiante(cod_asig, ep.getNivel());
        return ResponseEntity.ok(datos);
    }


    // obtener el nro de estudiantes inscritos en un curso




}
