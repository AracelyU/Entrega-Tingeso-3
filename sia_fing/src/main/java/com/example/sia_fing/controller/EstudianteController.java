package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Carrera;
import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "false")
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes(){
        List<Estudiante> e = estudianteService.obtenerEstudiantes();
        if(e.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(e);
    }

    // eliminar estudiantes
    @PostMapping("/deleteAll")
    public ResponseEntity<List<Estudiante>> eliminarEstudiantes(){
        estudianteService.eliminarEstudiantes();
        List<Estudiante> e = estudianteService.obtenerEstudiantes();
        return ResponseEntity.ok(e);
    }
}
