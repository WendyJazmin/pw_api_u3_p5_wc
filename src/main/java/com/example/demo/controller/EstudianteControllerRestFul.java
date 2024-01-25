package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;

//API: un api siempre viene determinado por un proyecto

//Servicio-controller: Clase Controller
@RestController//Servicio
//se tiene que crear una URL
public class EstudianteControllerRestFul {
	
	//las capacidades vienen representadas por métdos de una clase
	public void guardar(Estudiante estudiante) {
		
	}

}
