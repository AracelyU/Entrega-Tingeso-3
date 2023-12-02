package com.example.sia_fing.service;


import com.example.sia_fing.entity.Estudiante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_curso;  // para crear un curso, luego se le asigna el id a notas cuando el estudiante se registra
    Integer cod_asig;
    String nom_curso;
    Integer cupos;






}
