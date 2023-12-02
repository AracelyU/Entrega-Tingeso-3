package com.example.sia_fing.controller;

import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.entity.Prerrequisito;
import com.example.sia_fing.service.EstudiantePrincipalService;
import com.example.sia_fing.service.PlanEstudioService;
import com.example.sia_fing.service.PrerrequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prerrequisito")
public class PrerrequisitosController {

    @Autowired
    PrerrequisitoService prerrequisitoService;

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Prerrequisito>> obtenerPrerrequisitos(){
        List<Prerrequisito> p = prerrequisitoService.obtenerPrerrequisitos();
        if(p.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<Prerrequisito>> eliminarPrerrequisitos(){
        prerrequisitoService.eliminarPrerrequisitos();
        List<Prerrequisito> p = prerrequisitoService.obtenerPrerrequisitos();
        return ResponseEntity.ok(p);
    }


    /*
    verificar si cumple todos los requisitos para un ramo
     */
    @GetMapping("/verifityPrerrequisito/{cod_asig}")
    public ResponseEntity<Integer> verificarPrerrequisito(@PathVariable("cod_asig") Integer cod_asig){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        Integer res = prerrequisitoService.verificarPrerrequisitos(cod_asig, ep.getRut());
        return ResponseEntity.ok(res);
    }

}
