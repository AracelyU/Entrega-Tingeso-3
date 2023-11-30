package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    public int estudiantePrincipal = 10;


    @Autowired
    EstudianteRepository estudianteRepository;

    public List<Estudiante> obtenerEstudiantes(){
        return estudianteRepository.findAll();
    }

    // eliminar estudiantes
    public void eliminarEstudiantes(){
        estudianteRepository.deleteAll();
    }


    /*
    función para guardar la información de los estudiantes en la base de datos
    */
    public void guardarEstudiante(String rut, String nombre, String apellido, String email, Integer cod_carr){
        Estudiante e = new Estudiante();
        e.setRut(rut);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setEmail(email);
        e.setCod_carr(cod_carr);
        estudianteRepository.save(e);
    }


    // obtener estudiante escogido
    public Estudiante obtenerEstudiantePrincipal(){
        return estudianteRepository.findEstudianteById(estudiantePrincipal);
    }

    // obtener la información relevante del estudiante escogido siendo esta
    /*
        nivel del alumno
        plan que cursa
        cursos que tiene inscritos
        cursos que puede tomar según las condiciones presentadas en el documento



     */







}
