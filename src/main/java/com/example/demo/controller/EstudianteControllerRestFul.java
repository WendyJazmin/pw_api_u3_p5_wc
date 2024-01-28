package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

//API: un api siempre viene determinado por un proyecto

//Servicio-controller: Clase Controller
@RestController//Servicio
//se tiene que crear una URL
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestFul {

    @Autowired
    private IEstudianteService estudianteService;
 
    //las capacidades vienen representadas por métdos de una clase
 
    //GET
    //http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
    //solo las get se pueden consumir desde el navegador
    @GetMapping(path="/buscar")
    public Estudiante buscar(){
       
        return this.estudianteService.buscar(2);
    }
 
    
 
    @PostMapping(path="/guardar")
    public void guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
    }

	
	

}