package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import com.example.sia_fing.entity.EstudiantePrincipal;
import com.example.sia_fing.entity.Horario;
import com.example.sia_fing.entity.Nota;
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
    PlanEstudioService planEstudioService;

    @Autowired
    EstudiantePrincipalService estudiantePrincipalService;


    // obtener los horarios de los ramos que tiene inscrito
    public List<Horario> obtenerHorariosInscribir(){
        List<Nota> ramos = notaService.ramosInscritos();
        List<Horario> h = new ArrayList<>();
        for(Nota n : ramos){
            Horario horario = obtenerHorario(n.getCod_asig(), n.getSeccion());
            if(horario != null){

                // calcular los alumnos inscritos
                Integer cupos = notaService.nroInscritos(n.getCod_asig(), n.getSeccion());
                horario.setCupos(cupos);
                horarioRepository.save(horario);
                h.add(horario);
            }
        }
        return h;
    }


    // mostrar horarios
    public List<Horario> obtenerHorarios(){
        return horarioRepository.findAll();
    }


    // obtener horarios de una asignatura y sección

    public void eliminarHorarios(){
        horarioRepository.deleteAll();
    }


    // ver si dos modulos son iguales
    public Integer verificarTope(Integer cod_asig, String seccion){

        // obtener todos los horarios de los ramos que da el estudiante
        // para ello necesito conseguir las asignaturas de los ramos que esta dando el estudiante
        List<Nota> ramosInscritos = notaService.ramosInscritos();

        // obtengo el horario de la asignatura a ingresad
        Horario ho = horarioRepository.horarioEstudiante(cod_asig, seccion);

        if(ho == null){
            return -1;
        }
        System.out.println("tiene horario ho");

        List<Horario> horarios = new ArrayList<>();
        for(Nota n : ramosInscritos){
            Horario h = horarioRepository.horarioEstudiante(n.getCod_asig(), n.getSeccion());
            if(h != null){
                horarios.add(h);
            }
        }

        horarios.add(ho);
        int bandera;

        for(int modulo=1; modulo < 7; modulo++){
            switch (modulo){
                case 1:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo1().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 1");
                            return 1; // hay tope
                        }
                    }
                    break;

                case 2:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo2().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 2");
                            return 2; // hay tope
                        }
                    }
                    break;

                case 3:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo3().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 3");
                            return 3; // hay tope
                        }
                    }
                    break;
                case 4:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo4().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 4");
                            return 4; // hay tope
                        }
                    }
                    break;
                case 5:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo5().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 5");
                            return 5; // hay tope
                        }
                    }
                    break;
                case 6:
                    for(int dia=0; dia < 6; dia++){
                        bandera = 0;
                        for(Horario h: horarios){
                            if(h.getModulo6().get(dia) == 1){
                                bandera++;
                            }
                        }

                        if(bandera < 1){
                            System.out.println("tope en modulo 6");
                            return 6; // hay tope
                        }
                    }
                    break;
                default:
                    System.out.println("error");
                    break;
            }

        }


        return 0;  // no hay tope
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


        String nombre = planEstudioService.nombreRamo(cod_asig);

        // verificar si la asignatura tiene horario
        Horario horario = obtenerHorario(cod_asig, seccion);

        if(horario == null){  // se crea nuevo horario
            System.out.println("Se tuvo que crear nuevo horario para :" + cod_asig);
            Horario h = new Horario();
            h.setCod_asig(cod_asig);
            h.setSeccion(seccion);
            h.setNom_asig(nombre);


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

        }else{
            System.out.println("Encontro el horario de " + cod_asig);
            System.out.println("modulo: " + modulo);
            System.out.println("dia "  + dia);
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
    }



    // eliminar horario especifico
    public void eliminarHorarioEspecifico(Horario h){
        horarioRepository.delete(h);
    }



}
