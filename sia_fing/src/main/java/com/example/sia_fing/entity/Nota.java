package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer anio;
    Integer semestre;
    String rut;
    Integer cod_asig;
    Float nota;
    String seccion;
}
