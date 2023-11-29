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

    public void guardarNota(int anio, int semestre, String cod_alumno, int nivel, String cod_asig){
        Nota n = new Nota();
        n.setAnio(anio);
        n.setSemestre(semestre);
        n.setCod_alumno(cod_alumno);
        n.setNivel(nivel);
        n.setCod_asig(cod_asig);
        notaRepository.save(n);
    }


}
