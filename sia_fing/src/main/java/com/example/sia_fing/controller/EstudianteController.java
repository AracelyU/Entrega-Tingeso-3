package com.example.sia_fing.controller;


import com.example.sia_fing.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

}
