package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "carreras")

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int cod_carr;
    String nom_carr;
}
