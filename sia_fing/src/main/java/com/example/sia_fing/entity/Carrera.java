package com.example.sia_fing.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "carreras")

public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer codigo_carr;
    String nom_carr;



}
