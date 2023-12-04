package com.example.sia_fing.service;


import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    NotaService notaService;


    // mostrar horarios
    public List<Horario> obtenerHorarios(){
        return horarioRepository.findAll();
    }

    public void eliminarHorarios(){
        horarioRepository.deleteAll();
    }

    // guardar horario
    public Horario guardarHorario(String dia, Integer modulo, Integer cod_asig){
        Horario h = new Horario();
        h.setDia(dia);
        h.setModulo(modulo);
        h.setCod_asig(cod_asig);
        horarioRepository.save(h);
        return h;
    }

    // eliminar horario especifico
    public void eliminarHorarioEspecifico(Horario h){
        horarioRepository.delete(h);
    }


    // verificar que no hay tope de horario entre los horarios
    public Integer verificarTope(Integer cod_asig, Integer anio, Integer semestre){
        List<Integer> ramosDelEstudianteActual = notaService.ramosAnioSemestre(anio, semestre);


        return -1;
    }




}
