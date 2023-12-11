package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ElementCollection
    List<Integer> modulo1 = Arrays.asList(0, 0, 0, 0, 0, 0);

    @ElementCollection
    List<Integer> modulo2 = Arrays.asList(0, 0, 0, 0, 0, 0);

    @ElementCollection
    List<Integer> modulo3 = Arrays.asList(0, 0, 0, 0, 0, 0);

    @ElementCollection
    List<Integer> modulo4 = Arrays.asList(0, 0, 0, 0, 0, 0);

    @ElementCollection
    List<Integer> modulo5 = Arrays.asList(0, 0, 0, 0, 0, 0);

    @ElementCollection
    List<Integer> modulo6 = Arrays.asList(0, 0, 0, 0, 0, 0);

    Integer cod_asig; // una asignatura se enlaza a un horario
    String nom_asig;
    Integer cupos;
    String texto;
    String seccion;

}
