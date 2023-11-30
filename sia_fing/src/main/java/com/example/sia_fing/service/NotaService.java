package com.example.sia_fing.service;


import com.example.sia_fing.entity.Nota;
import com.example.sia_fing.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    @Autowired
    NotaRepository notaRepository;

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


}
