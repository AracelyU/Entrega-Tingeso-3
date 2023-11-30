package com.example.sia_fing.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "prerrequisitos")
public class Prerrequisito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer cod_asig;
    Integer cod_prerreq;

}
