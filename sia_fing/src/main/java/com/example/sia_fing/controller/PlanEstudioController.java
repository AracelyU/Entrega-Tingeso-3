package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.service.PlanEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ramo")
public class PlanEstudioController {

    @Autowired
    PlanEstudioService planEstudioService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PlanEstudio>> obtenerRamos(){
        List<PlanEstudio> p = planEstudioService.obtenerPlanEstudios();
        if(p.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<PlanEstudio>> eliminarRamos(){
        planEstudioService.eliminarPlanEstudios();
        List<PlanEstudio> p = planEstudioService.obtenerPlanEstudios();
        return ResponseEntity.ok(p);
    }

}
