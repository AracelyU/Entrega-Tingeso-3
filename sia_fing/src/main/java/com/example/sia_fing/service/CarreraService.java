package com.example.sia_fing.service;


import com.example.sia_fing.entity.Carrera;
import com.example.sia_fing.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    CarreraRepository carreraRepository;

    public List<Carrera> obtenerCarreras(){
        return carreraRepository.findAll();
    }

    /*
    función para guardar una carrera
    */
    public void guardarCarrera(String cod_carr, String nombre){
        Carrera c = new Carrera();
        c.setCod_carr(cod_carr);
        c.setNom_carr(nombre);
        carreraRepository.save(c);
    }

}
