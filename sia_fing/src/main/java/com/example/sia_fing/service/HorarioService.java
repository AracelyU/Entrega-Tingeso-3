package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    // obtener horarios de una asignatura y sección

    public void eliminarHorarios(){
        horarioRepository.deleteAll();
    }


    // ver si dos modulos son iguales
    public Integer verificarTope(Integer dia, Integer modulo, Integer anio, Integer semestre, String seccion){

        // obtener todos los horarios de los ramos que da el estudiante
        // para ello necesito conseguir las asignaturas de los ramos que esta dando el estudiante
        List<Integer> codigos_asignaturas = notaService.ramosAnioSemestre(anio, semestre);

        List<Horario> horarios = new ArrayList<>();
        for(Integer cod_asig : codigos_asignaturas){
            Horario h = horarioRepository.horarioEstudiante(cod_asig, seccion);
            if(h != null){
                horarios.add(h);
            }
        }

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
    public Horario crearHorario(Integer cod_asig, String seccion){
        Horario h = new Horario();
        h.setCod_asig(cod_asig);
        h.setSeccion(seccion);
        horarioRepository.save(h);
        return h;
    }



    // obtener horario de asignatura por seccion
    public Horario obtenerHorario(Integer cod_asig, String seccion){
        return horarioRepository.horarioEstudiante(cod_asig, seccion);
    }

    // guardar horario
    public Horario guardarHorario(Integer dia, Integer modulo, Integer cod_asig, String seccion){

        EstudiantePrincipal ep = estudiantePrincipalService.obtenerEstudiantePrincipal();
        if(ep == null){
            System.out.println("no se ingreso estudiante");
            return null;
        }

        // mejor verlo desde el frontend
        // verificar que no se genere tope entre los horarios de las asignaturas del mismo estudiante
        // esto viendo que para todos los horarios registrados de los cursos del estudiante no
        // se este ocupando el mismo módulo
        Integer tope = verificarTope(dia, modulo, ep.getAnio(), ep.getSemestre(), seccion);

        //if(tope == 1){
        //    System.out.println("se genero tope");
        //    return null;
        //}

        // verificar si la asignatura tiene horario
        Horario horario = obtenerHorario(cod_asig, seccion);

        if(horario == null){  // se crea nuevo horario
            System.out.println("Se tuvo que crear nuevo horario para :" + cod_asig);
            Horario h = new Horario();
            h.setCod_asig(cod_asig);
            h.setSeccion(seccion);
            horarioRepository.save(h);
            return h;
        }

        System.out.println("Encontro el horario de " + cod_asig);

        // de lo contrario ya existe un horario para esa asignatura y se necesita actualizar
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
