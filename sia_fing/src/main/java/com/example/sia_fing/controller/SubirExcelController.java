package com.example.sia_fing.controller;


import com.example.sia_fing.service.SubirExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/excel")
public class SubirExcelController {

    @Autowired
    private SubirExcelService subirExcelService;

    @PostMapping("/uploadExcel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            subirExcelService.leerEstudiantes(file);
            return ResponseEntity.ok("Archivo leído correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
