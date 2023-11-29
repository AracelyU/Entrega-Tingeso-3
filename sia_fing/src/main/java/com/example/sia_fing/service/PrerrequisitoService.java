package com.example.sia_fing.service;


import com.example.sia_fing.entity.Prerrequisito;
import com.example.sia_fing.repository.PrerrequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerrequisitoService {

    @Autowired
    PrerrequisitoRepository prerrequisitoRepository;

    public List<Prerrequisito> obtenerPrerrequisitos(){
        return prerrequisitoRepository.findAll();
    }

    public void guardarPrerrequisito(String cod_asig, String cod_prerreq){
        Prerrequisito p = new Prerrequisito();
        p.setCod_asig(cod_asig);
        p.setCod_prerreq(cod_prerreq);
        prerrequisitoRepository.save(p);

    }
}
