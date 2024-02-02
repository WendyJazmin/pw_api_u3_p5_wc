package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


//API: un api siempre viene determinado por un proyecto

//Servicio-controller: Clase Controller
@RestController//Servicio
//se tiene que crear una URL
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestFul {

    @Autowired
    private IEstudianteService estudianteService;
 
    //las capacidades vienen representadas por métdos de una clase
 
   //Path Variable

   
   
    //GET
    //http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
    //solo las get se pueden consumir desde el navegador
    @GetMapping(path="/{id}")
    public Estudiante buscar(@PathVariable Integer id){
        return this.estudianteService.buscar(id);
    }

    //CONSULTANDO TODOS LOS ESTUDIANTES
    //http://localhost:8080/API/v1.0/Matricula/estudiantes GET
    @GetMapping//el consultar todos no necesita un path
    public List<Estudiante> consultarTodos(@RequestParam (required=false, defaultValue="masculino") String genero){
       
        return this.estudianteService.buscarTodos(genero);
    }

    
    @PostMapping
    public void guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
    }

    @PutMapping(path="/{id}")
    public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
        estudiante.setId(id);
    	this.estudianteService.actualizar(estudiante);
    }
    
    //http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
    @PatchMapping(path="/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante,  @PathVariable Integer id){
        this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);

    }

    
    @DeleteMapping(path="/{id}")
    public void borrar(@PathVariable Integer id){
        this.estudianteService.borrar(id);
    }
	

}