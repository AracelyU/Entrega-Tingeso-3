package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    NotaService notaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Nota>> obtenerNotas(){
        List<Nota> n = notaService.obtenerNotas();
        if(n.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(n);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<Nota>> eliminarNotas(){
        notaService.eliminarNotas();
        List<Nota> n = notaService.obtenerNotas();
        return ResponseEntity.ok(n);
    }
}
