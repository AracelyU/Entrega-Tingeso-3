package com.example.sia_fing.service;


import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    determinar nuevo año academico con anio y semestre
     */
    public List<Integer> newAgeAcademy(){
        EstudiantePrincipal e = estudiantePrincipalService.obtenerEstudiantePrincipal();
        List<Integer> datos = notaRepository.newAgeAcademy(e.getRut());
        e.setAnio(datos.get(0));
        e.setSemestre(datos.get(1)); // actualizar año y semestre del alumno falta verificar
        return datos;

    }




}
