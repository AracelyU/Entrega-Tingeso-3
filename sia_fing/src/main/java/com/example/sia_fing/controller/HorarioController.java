package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@CrossOrigin(origins = "false")
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioService horarioService;

    @GetMapping("/getAll")   // mostrar todos los horarios
    public ResponseEntity<List<Horario>> obtenerHorarios(){
        List<Horario> h = horarioService.obtenerHorarios();
        if(h.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(h);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<List<Horario>> eliminarHorarios(){
        horarioService.eliminarHorarios();
        List<Horario> h = horarioService.obtenerHorarios();
        return ResponseEntity.ok(h);
    }

    @GetMapping("/getHorarios/{cod_asig}/{seccion}")
    public ResponseEntity<Horario> getHorarios(@PathVariable("cod_asig") Integer cod_asig,
                                               @PathVariable("seccion") String seccion){

        Horario h = horarioService.obtenerHorario(cod_asig, seccion);
        if(h == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(h);
    }


    // crea el horario
    @PostMapping("/createHorario/{dia}/{modulo}/{cod_asig}/{seccion}")
    public ResponseEntity<Horario> crearHorario(@PathVariable("dia") Integer dia,
                                                @PathVariable("modulo") Integer modulo,
                                                @PathVariable("cod_asig") Integer cod_asig,
                                                @PathVariable("seccion") String seccion){
        Horario h = horarioService.guardarHorario(dia, modulo, cod_asig, seccion);
        if(h == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(h);
    }



}
