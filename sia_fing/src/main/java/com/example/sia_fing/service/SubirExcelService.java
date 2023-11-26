package com.example.sia_fing.service;


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
    EstudianteRepository estudianteRepository;

    @Autowired
    CarreraRepository carreraRepository;

    @Autowired
    PlanEstudioRepository planEstudioRepository;

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
                int cod_carr = (int) row.getCell(4).getNumericCellValue(); // <-- Corregido

                if (!rut.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty()) {
                    System.out.println(rut + ";" + nombres + "; " + apellidos + ";" + email + ";" + cod_carr);
                    // Guardar en la base de datos: guardarDataDB(rut, nombres, apellidos, email, cod_carr);
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
                int cod_carr = (int) row.getCell(4).getNumericCellValue(); // <-- Corregido

                if (!rut.isEmpty() && !nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty()) {
                    System.out.println(rut + ";" + nombres + "; " + apellidos + ";" + email + ";" + cod_carr);
                    // Guardar en la base de datos: guardarDataDB(rut, nombres, apellidos, email, cod_carr);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }



}
