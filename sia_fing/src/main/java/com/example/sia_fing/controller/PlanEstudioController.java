package com.example.sia_fing.controller;


import com.example.sia_fing.service.PlanEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlanEstudioController {

    @Autowired
    PlanEstudioService planEstudioService;

}
