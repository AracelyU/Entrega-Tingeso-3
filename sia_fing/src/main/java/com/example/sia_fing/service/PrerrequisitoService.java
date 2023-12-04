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
    public Prerrequisito guardarPrerrequisito(Integer cod_asig, Integer cod_prerreq){
        Prerrequisito p = new Prerrequisito();
        p.setCod_asig(cod_asig);
        p.setCod_prerreq(cod_prerreq);
        prerrequisitoRepository.save(p);
        return p;
    }

    // verificar requisitos
    public List<Integer> obtenerPrerrequisitos(Integer cod_carr){
        return prerrequisitoRepository.findPrerrequisitoByCod_carr(cod_carr);
    }



}
