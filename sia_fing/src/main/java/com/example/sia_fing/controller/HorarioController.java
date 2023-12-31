package com.example.sia_fing.controller;


import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.service.HorarioService;
import com.example.sia_fing.service.PlanEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    HorarioService horarioService;


    // obtener los horarios de ramos inscritos
    @GetMapping("/getHorariosInscribir")
    public ResponseEntity<List<Horario>> obtenerHorariosInscribir(){
        List<Horario> h = horarioService.obtenerHorariosInscribir();
        if(h == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(h);
    }

    @GetMapping("/getAll")   // mostrar todos los horarios
    public ResponseEntity<List<Horario>> obtenerHorarios(){
        List<Horario> h = horarioService.obtenerHorarios();
        if(h.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(h);
    }

    // verifica si hay tope
    @GetMapping("/verTope/{cod_asig}/{seccion}")
    public ResponseEntity<Integer> verTope(@PathVariable("cod_asig") Integer cod_asig,
                                           @PathVariable("seccion") String seccion){
        Integer op = horarioService.verificarTope(cod_asig, seccion);
        System.out.println("estado tope: " + op);
        return ResponseEntity.ok(op);
    }



    @PostMapping("/deleteAll")
    public ResponseEntity<List<Horario>> eliminarHorarios(){
        horarioService.eliminarHorarios();
        List<Horario> h = horarioService.obtenerHorarios();
        return ResponseEntity.ok(h);
    }

    // obtener cupos de un ramo por seccion
    @GetMapping("/getCupos/{cod_asig}/{seccion}")
    public ResponseEntity<Integer> cuposDisponibles(@PathVariable("cod_asig") Integer cod_asig,
                                                    @PathVariable("seccion") String seccion){
        System.out.println("se busca obtener los cupos");
        Integer nroInscritos = horarioService.obtenerCupos(cod_asig,seccion);
        System.out.println("se obtuvieron: " + nroInscritos);
        return ResponseEntity.ok(nroInscritos);
    }

    @GetMapping("/getHorarios/{cod_asig}/{seccion}")
    public ResponseEntity<Horario> getHorario(@PathVariable("cod_asig") Integer cod_asig,
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
