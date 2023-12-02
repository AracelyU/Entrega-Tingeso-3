package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.service.EstudiantePrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantePrincipal")
public class EstudiantePrincipalController {

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    // ingresar estudiante
    @GetMapping("/getAll")
    public ResponseEntity<List<EstudiantePrincipal>> obtenerEstudiantes(){
        List<EstudiantePrincipal> e = estudiantePrincipalService.obtenerEstudiante();
        if(e.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(e);
    }

    // eliminar estudiante
    @PostMapping("/deleteAll")
    public ResponseEntity<List<EstudiantePrincipal>> eliminarEstudiantes(){
        estudiantePrincipalService.eliminarEstudiante();
        List<EstudiantePrincipal> e = estudiantePrincipalService.obtenerEstudiante();
        return ResponseEntity.ok(e);
    }

    // agregar un estudiante
    @PostMapping("/create/{rut}")
    public ResponseEntity<EstudiantePrincipal> crearEstudiantePrincipal(@PathVariable("rut") String rut){
        EstudiantePrincipal ep = estudiantePrincipalService.crearEstudiantePrincipal(rut);
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ep);
    }

    // ver el estudiante principal
    @GetMapping("/getEstudiante")
    public ResponseEntity<EstudiantePrincipal> obtenerEstudiantePrincipal(){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ep);
    }

}
