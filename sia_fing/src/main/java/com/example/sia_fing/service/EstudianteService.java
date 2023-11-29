package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    public List<Estudiante> obtenerEstudiantes(){
        return estudianteRepository.findAll();
    }

    /*
    función para guardar la información de los estudiantes en la base de datos
    */
    public void guardarEstudiante(String rut, String nombre, String apellido, String email, String cod_carr){
        Estudiante e = new Estudiante();
        e.setRut(rut);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setEmail(email);
        e.setCod_carr(cod_carr);
        estudianteRepository.save(e);
    }


}
