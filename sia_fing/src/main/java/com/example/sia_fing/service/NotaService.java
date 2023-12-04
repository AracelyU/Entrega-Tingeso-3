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


    public List<Nota> obtenerNotas(){
        return notaRepository.findAll();
    }

    public void eliminarNotas(){ notaRepository.deleteAll();}

    // recuerda que hay un atributo sección para más tarde
    public Nota guardarNota(Integer anio, Integer semestre, String rut, Integer cod_asig, double nota){
        Nota n = new Nota();
        n.setAnio(anio);
        n.setSemestre(semestre);
        n.setRut(rut);
        n.setCod_asig(cod_asig);
        n.setNota((float) nota);
        notaRepository.save(n);
        return n;
    }

    /*
    determinar nuevo año academico con anio y semestre (a la hora de ingresar el ramo al estudiante -> inscribir ramo como un objeto nota pero con nota vacio 0 -1)
     */
    public List<Integer> newAgeAcademy(){
        EstudiantePrincipal e = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(e == null){
            return null;
        }

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
    obtener nota de un ramo según su código de asignatura (ayuda a verificar si cumple prerrequisitos)
     */
    public Float obtenerNotaDeRamo(Integer cod_asig){
        EstudiantePrincipal e = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(e == null){
            return null;
        }
        return notaRepository.NotaByCod_asig(cod_asig, e.getRut());
    }



    /*
    ver la situación de repitencia de un estudiante
     */
    public Integer situacionRepitenciaEstudiante(Integer cod_asig, Integer nivel){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return -1; // no hay estudiante registrado
        }
        Integer nro_intentos = notaRepository.nroVecesQueDioUnRamo(ep.getRut(), cod_asig);

        if(nro_intentos <= 2){
            return 1; // puede inscribir el ramo
        }

        if(nro_intentos <= 3 && nivel == 1){
            return 1; // puede inscribir ramo hasta 3 veces los de nivel 1
        }

        return 0; // reprobación de la asignatura
    }

    /*
    obtiene los codigos de las asignaturas que esta dando el estudiante
     */
    public List<Integer> ramosAnioSemestre(Integer anio, Integer semestre){
        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            return null; // no hay estudiante registrado
        }
        return notaRepository.RamosAnioSemestre(anio, semestre, ep.getRut());
    }


    // función de numeroRamos
    public Integer numeroRamos(Integer anio, Integer semestre, String rut){
        return notaRepository.numeroRamos(anio, semestre, rut);
    }




}
