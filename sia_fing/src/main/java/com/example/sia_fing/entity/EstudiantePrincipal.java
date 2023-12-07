package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "estudiante")
public class EstudiantePrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String rut; // el id del estudiante registrado en la página
    String nombre;
    String apellido;
    String email;
    Integer nivel;
    Integer cod_carr;
    String nom_carr;
    Integer anio;
    Integer semestre;

}
