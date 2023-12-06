package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
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

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;


    // mostrar horarios
    public List<Horario> obtenerHorarios(){
        return horarioRepository.findAll();
    }

    public void eliminarHorarios(){
        horarioRepository.deleteAll();
    }


    // ver si dos modulos son iguales
    public Integer verificarTope(String rut, Integer dia, Integer modulo){
        List<Horario> horarios = horarioRepository.horariosEstudiante(rut);

        for(Horario h : horarios){
            switch (modulo){
                case 1:
                    if(h.getModulo1().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                case 2:
                    if(h.getModulo2().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                case 3:
                    if(h.getModulo3().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                case 4:
                    if(h.getModulo4().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                case 5:
                    if(h.getModulo5().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                case 6:
                    if(h.getModulo6().get(dia-1) == 1){
                        return 1; // hay tope
                    }
                    break;
                default:
                    System.out.println("error");
                    break;
            }
        }

        return 0;  // no hay tope
    }



    // guardar horario
    public Horario guardarHorario(Integer dia, Integer modulo, Integer cod_asig){

        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            System.out.println("no se ingreso estudiante");
            return null;
        }

        // verificar que no se genere tope entre los horarios de las asignaturas del mismo estudiante
        // esto viendo que para todos los horarios registrados de los cursos del estudiante no
        // se este ocupando el mismo módulo
        Integer tope = verificarTope(ep.getRut(), dia, modulo);

        if(tope == 1){
            System.out.println("se genero tope");
            return null;
        }

        // verificar si la asignatura tiene horario
        Horario horario = horarioRepository.horarioEstudiante(ep.getRut(), cod_asig);

        if(horario == null){  // se crea nuevo horario y se actualiza con el nuevo modulo
            Horario h = new Horario();
            h.setRut(ep.getRut());
            h.setCod_asig(cod_asig);
            switch (modulo){
                case 1:
                    h.getModulo1().set(dia-1, 1);
                    break;
                case 2:
                    h.getModulo2().set(dia-1, 1);
                    break;
                case 3:
                    h.getModulo3().set(dia-1, 1);
                    break;
                case 4:
                    h.getModulo4().set(dia-1, 1);
                    break;
                case 5:
                    h.getModulo5().set(dia-1, 1);
                    break;
                case 6:
                    h.getModulo6().set(dia-1, 1);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            horarioRepository.save(h);
            return h;
        }

        // de lo contrario ya existe un horario para esa asignatura
        switch (modulo){
            case 1:
                horario.getModulo1().set(dia-1, 1);
                break;
            case 2:
                horario.getModulo2().set(dia-1, 1);
                break;
            case 3:
                horario.getModulo3().set(dia-1, 1);
                break;
            case 4:
                horario.getModulo4().set(dia-1, 1);
                break;
            case 5:
                horario.getModulo5().set(dia-1, 1);
                break;
            case 6:
                horario.getModulo6().set(dia-1, 1);
                break;
            default:
                System.out.println("error");
                break;
        }
        horarioRepository.save(horario);
        return horario;
    }



    // eliminar horario especifico
    public void eliminarHorarioEspecifico(Horario h){
        horarioRepository.delete(h);
    }



}
