package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.PlanEstudio;
import com.example.sia_fing.repository.EstudiantePrincipalRepository;
import com.example.sia_fing.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudiantePrincipalService {

    @Autowired
    EstudianteService estudianteService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    EstudiantePrincipalRepository estudiantePrincipalRepository;


    // ver estudiante
    public List<EstudiantePrincipal> obtenerEstudiante(){
        return estudiantePrincipalRepository.findAll();
    }


    // eliminar estudiante principal
    public void eliminarEstudiante(){
        estudiantePrincipalRepository.deleteAll();
    }


    // crear un estudiante principal
    public EstudiantePrincipal crearEstudiantePrincipal(String rut){
        Estudiante e = estudianteService.obtenerEstudianteRut(rut);

        if(e == null){
            return null;
        }

        EstudiantePrincipal ep = new EstudiantePrincipal();

        // información del estudiante
        ep.setRut(e.getRut());
        ep.setCod_carr(e.getCod_carr());
        ep.setNombre(e.getNombre());
        ep.setApellido(e.getApellido());
        ep.setNivel(6);  // hacer función para identificar el nivel de estudiante
        ep.setEmail(e.getEmail());

        // información de su carrera
        String nom_carr = carreraService.nombreCarrera(e.getCod_carr());
        ep.setNom_carr(nom_carr);

        // información de las carreras que puede tomar porque cumple con los prerrequisitos
        List<PlanEstudio> ramos;


        estudiantePrincipalRepository.save(ep);
        return ep;

    }

    public EstudiantePrincipal obtenerEstudiantePrincipal(){
        return estudiantePrincipalRepository.obtenerEstudiantePrincipal();
    }




}
