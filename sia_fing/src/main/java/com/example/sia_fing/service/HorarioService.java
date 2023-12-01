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

    // mostrar horarios
    public List<Horario> obtenerHorarios(){
        return horarioRepository.findAll();
    }

    public void eliminarHorariod(){
        horarioRepository.deleteAll();
    }

    // guardar horario
    public void guardarHorario(String dia, Integer modulo, Integer cod_asig){
        Horario h = new Horario();
        h.setDia(dia);
        h.setModulo(modulo);
        h.setCod_asig(cod_asig);
        horarioRepository.save(h);
    }

    // eliminar horario especifico
    public void eliminarHorarioEspecifico(Horario h){
        horarioRepository.delete(h);
    }




}
