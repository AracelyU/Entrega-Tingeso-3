package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String dia;
    Integer modulo;
    List<Integer> modulo1;
    Integer cod_asig; // una asignatura se enlaza a un horario

}
