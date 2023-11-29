package com.example.sia_fing.service;


import com.example.sia_fing.entity.Carrera;
import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.repository.CarreraRepository;
import com.example.sia_fing.repository.EstudianteRepository;
import com.example.sia_fing.repository.PlanEstudioRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
Servicio que lee y guarda los archivos excel y los coloca en la base de datos
 */
@Service
public class SubirExcelService {

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

    public String guardar(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (!filename.toLowerCase().contains(".xlsx")) {
            return "No se ingresó un archivo .xlsx";
        }

        if (filename != null) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    System.out.println("Archivo .xlsx guardado");
                } catch (IOException e) {
                    System.out.println("Error al guardar el archivo");
                }
            }
            return "Archivo .xlsx guardado con éxito!";
        } else {
            return "No se pudo guardar el archivo";
        }
    }

    /*
    lee el archivo Estudiantes.xlsx
     */
    public void leerEstudiantes(MultipartFile file) {
        System.out.println("Intentar leer el contenido");
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) { // Skip the header row
                Row row = sheet.getRow(rowIndex);

                String rut = row.getCell(0).getStringCellValue();
                String nombres = row.getCell(1).getStringCellValue();
                String apellidos = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue(); // <-- Corregido
                String cod_carr = row.getCell(4).getStringCellValue(); // <-- Corregido

                if (!rut.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty()) {
                    estudianteService.guardarEstudiante(rut, nombres, apellidos, email, cod_carr);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }

    /*
    lee el archivo carreras.xlsx
     */
    public void leerCarreras(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) { // Skip the header row
                Row row = sheet.getRow(rowIndex);

                String cod_carr = row.getCell(0).getStringCellValue();
                String nombre = row.getCell(1).getStringCellValue();

                if (!nombre.isEmpty()) {
                    carreraService.guardarCarrera(cod_carr, nombre);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }

    /*
    lee el archivo nota.xlsx
     */
    public void leerNotas(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) { // Skip the header row
                Row row = sheet.getRow(rowIndex);

                int anio = (int) row.getCell(0).getNumericCellValue();
                int semestre = (int) row.getCell(1).getNumericCellValue();
                String cod_alumno = row.getCell(2).getStringCellValue();
                int nivel = (int) row.getCell(3).getNumericCellValue(); // <-- Corregido
                String cod_asig = row.getCell(4).getStringCellValue(); // <-- Corregido

                if (!cod_alumno.isEmpty()){
                   notaService.guardarNota(anio, semestre, cod_alumno, nivel, cod_asig);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }


    /*
lee el archivo nota.xlsx
 */
    public void leerPlanEstudio(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) { // Skip the header row
                Row row = sheet.getRow(rowIndex);

                String cod_carr = row.getCell(0).getStringCellValue();
                String cod_plan = row.getCell(1).getStringCellValue();
                int nivel = (int) row.getCell(2).getNumericCellValue(); // <-- Corregido
                String cod_asig = row.getCell(3).getStringCellValue(); // <-- Corregido
                String nom_asig = row.getCell(4).getStringCellValue();

                if (!nom_asig.isEmpty()){
                    planEstudioService.guardarPlanEstudio(cod_carr, cod_plan, nivel, cod_asig, nom_asig);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }

    public void leerPrerrequisitos(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) { // Skip the header row
                Row row = sheet.getRow(rowIndex);

                String cod_asig = row.getCell(0).getStringCellValue();
                String cod_prerreq = row.getCell(1).getStringCellValue();
                prerrequisitoService.guardarPrerrequisito(cod_asig, cod_prerreq);

            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }






}
