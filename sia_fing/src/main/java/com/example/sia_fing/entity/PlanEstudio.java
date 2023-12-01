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
@Table(name = "plan_estudios")
public class PlanEstudio {  // ramo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer cod_carr;
    String cod_plan;
    Integer nivel;
    Integer cod_asig;
    String nom_asig;
    Integer cupos = 50;


}
