package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Carrera;
import com.example.sia_fing.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    CarreraService carreraService;

    // mostrar las carreras
    @GetMapping("/getAll")
    public ResponseEntity<List<Carrera>> obtenerCarreras(){
        List<Carrera> c = carreraService.obtenerCarreras();
        if(c.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(c);
    }

    // eliminar carreras
    @PostMapping("/deleteAll")
    public ResponseEntity<List<Carrera>> eliminarCarreras(){
        carreraService.eliminarCarreras();
        List<Carrera> c = carreraService.obtenerCarreras();
        return ResponseEntity.ok(c);
    }

}
