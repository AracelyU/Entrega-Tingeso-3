package com.example.sia_fing.service;


import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.entity.Prerrequisito;
import com.example.sia_fing.repository.PrerrequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerrequisitoService {

    @Autowired
    PrerrequisitoRepository prerrequisitoRepository;

    @Autowired
    NotaService notaService;

    public List<Prerrequisito> obtenerPrerrequisitos(){
        return prerrequisitoRepository.findAll();
    }

    public void eliminarPrerrequisitos(){
        prerrequisitoRepository.deleteAll();
    }
    public void guardarPrerrequisito(Integer cod_asig, Integer cod_prerreq){
        Prerrequisito p = new Prerrequisito();
        p.setCod_asig(cod_asig);
        p.setCod_prerreq(cod_prerreq);
        prerrequisitoRepository.save(p);

    }

    /*
    verificar que se cumplen los prerrequisitos de un ramo
    */
    public Integer verificarPrerrequisitos(Integer cod_asig, String rut){
        // obtener los prerrequisitos de una carrera
        List<Integer> codigos_prerrequisitos = prerrequisitoRepository.findPrerrequisitoByCod_carr(cod_asig);
        if(codigos_prerrequisitos.isEmpty()){
            return -2; // no se encontraron prerrequisitos para este ramo
        }

        for(Integer c : codigos_prerrequisitos){
            // verificar si la nota de cada prerrequisitos existe y es mayor a 4
            Float nota = notaService.obtenerNotaDeRamo(c, rut);
            System.out.println("nota de asignatura: " + c + " tiene una nota: " + nota);
            if(nota == null){
                return -1; // no esta registrado a ese ramo
            }

            if(nota < 4){
                return 0; // no paso el ramo
            }
        }
        return 1; // cumple todos los requisitos
    }


}
