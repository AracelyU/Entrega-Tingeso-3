package com.example.sia_fing.service;


import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {
    @Autowired
    NotaRepository notaRepository;

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;

    @Autowired
    PlanEstudioService planEstudioService;


    public List<Nota> obtenerNotas(){
        return notaRepository.findAll();
    }

    public void eliminarNotas(){ notaRepository.deleteAll();}
    public void guardarNota(Integer anio, Integer semestre, String rut, Integer cod_asig, double nota){
        Nota n = new Nota();
        n.setAnio(anio);
        n.setSemestre(semestre);
        n.setRut(rut);
        n.setCod_asig(cod_asig);
        n.setNota((float) nota);
        notaRepository.save(n);
    }

    /*
    determinar nuevo año academico con anio y semestre (a la hora de ingresar el ramo al estudiante -> inscribir ramo como un objeto nota pero con nota vacio)
     */
    public List<Integer> newAgeAcademy(){
        EstudiantePrincipal e = estudiantePrincipalService.obtenerEstudiantePrincipal();
        List<Integer> datos = notaRepository.newAgeAcademy(e.getRut());
        Integer anio = datos.get(0);
        Integer semestre = datos.get(1);
        List<Integer> newDatos = new ArrayList<>();

        if(semestre == 1){
            newDatos.add(anio);
            newDatos.add(2);

        }else{
            Integer newAnio = anio+1;
            newDatos.add(newAnio);
            newDatos.add(1);
        }

        return newDatos;
    }


    /*
    obtener nota de un ramo según su código de asignatura
     */
    public Float obtenerNotaDeRamo(Integer cod_asig, String rut){
        return notaRepository.NotaByCod_asig(cod_asig, rut);
    }

    /*
     cuantos ramos tiene el estudiante según anio y semestre y estar al tanto de su situación de carrera

     verificar que curse al menos 3 asignaturas de su plan para permanecer en la carrera
     */
    public Integer numeroRamos(Integer anio, Integer semestre, String rut){
        Integer nro_ramos = notaRepository.numeroRamos(anio, semestre, rut);  // puede ser 0 o la cantidad de ramos
        if(nro_ramos < 3){
            return 0; // en peligro de no permanecer en su carrera
        }

        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return -1; // no hay estudiante registrado
        }

        Integer nro_ramos_max = planEstudioService.obtenerNroRamosNivel(ep.getNivel());

        if(nro_ramos > nro_ramos_max){
            return 2; // alcanzó el limite de ramos por inscribir
        }

        return 1; // su situación es estable
    }


    /*
    ver la situación de repitencia de un estudiante
     */
    public Integer situacionRepitenciaEstudiante(Integer cod_asig, String rut, Integer nivel){
        Integer nro_intentos = notaRepository.nroVecesQueDioUnRamo(rut, cod_asig);

        if(nro_intentos <= 2){
            return 1; // puede inscribir el ramo
        }

        if(nro_intentos <= 3 && nivel == 1){
            return 1; // puede inscribir ramo hasta 3 veces los de nivel 1
        }

        return 0; // reprobación de la asignatura
    }





    /*

     */




}
