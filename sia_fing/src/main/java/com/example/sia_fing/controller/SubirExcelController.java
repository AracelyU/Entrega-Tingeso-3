package com.example.sia_fing.controller;


import com.example.sia_fing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(allowCredentials = "false")
@RequestMapping("/excel")
public class SubirExcelController {

    @Autowired
    private SubirExcelService subirExcelService;

    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    NotaService notaService;

    @Autowired
    PlanEstudioService planEstudioService;

    @Autowired
    PrerrequisitoService prerrequisitoService;

    @PostMapping("/uploadEstudiantes")
    public ResponseEntity<String> uploadEstudiantes(@RequestParam("file") MultipartFile file) {
        try {
            if(estudianteService.obtenerEstudiantes().isEmpty()){
                subirExcelService.leerEstudiantes(file);
                return ResponseEntity.ok("Archivo leído correctamente");
            }else{
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }


    @PostMapping("/uploadCarreras")
    public ResponseEntity<String> uploadCarreras(@RequestParam("file") MultipartFile file) {
        try {
            if(carreraService.obtenerCarreras().isEmpty()){
                subirExcelService.leerCarreras(file);
                return ResponseEntity.ok("Archivo leído correctamente");
            }else{
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @PostMapping("/uploadNotas")
    public ResponseEntity<String> uploadNotas(@RequestParam("file") MultipartFile file) {
        try {
            if(notaService.obtenerNotas().isEmpty()){
                subirExcelService.leerNotas(file);
                return ResponseEntity.ok("Archivo leído correctamente");
            }else{
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @PostMapping("/uploadPlanEstudios")
    public ResponseEntity<String> uploadPlanEstudios(@RequestParam("file") MultipartFile file) {
        try {
            if(planEstudioService.obtenerPlanEstudios().isEmpty()){
                subirExcelService.leerPlanEstudio(file);
                return ResponseEntity.ok("Archivo leído correctamente");
            }else{
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @PostMapping("/uploadPrerrequisito")
    public ResponseEntity<String> uploadPrerrequisito(@RequestParam("file") MultipartFile file) {
        try {
            if(prerrequisitoService.obtenerPrerrequisitos().isEmpty()){
                subirExcelService.leerPrerrequisitos(file);
                return ResponseEntity.ok("Archivo leído correctamente");
            }else{
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }




}
