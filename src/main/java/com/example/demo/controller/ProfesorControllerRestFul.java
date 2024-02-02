package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.repository.modelo.Profesor;

import com.example.demo.service.IProfesorService;

@RestController//Servicio
@RequestMapping(path="/profesores")
public class ProfesorControllerRestFul {

  @Autowired
  private IProfesorService profesorService;

  //las capacidades vienen representadas por métdos de una clase

 //Path Variable
 
  //GET
  //http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
  //solo las get se pueden consumir desde el navegador
  @GetMapping(path="/buscar/{id}")
  public Profesor buscar(@PathVariable Integer id){
     
      return this.profesorService.buscar(id);
  }
  
  @GetMapping(path="/buscarMaterias/{materias}")
  public List<Profesor> buscarMaterias(@PathVariable Integer materias){
     
      return this.profesorService.buscarMaterias(materias);
  }

  @GetMapping(path="/consultarTodos")
  public List<Profesor> consultarTodos(@RequestParam String titulo){
     
      return this.profesorService.buscarTodos(titulo);
  }

  
  @GetMapping(path="/buscarPorSalario")
  public List<Profesor> buscarPorSalario(@RequestParam BigDecimal salario){
     
      return this.profesorService.buscarPorSalario(salario);
  }
  @PostMapping(path="/guardar")
  public void guardar(@RequestBody Profesor profesor) {
      this.profesorService.guardar(profesor);
  }

  @PutMapping(path="/actualizar")
  public void actualizar(@RequestBody Profesor profesor) {
      this.profesorService.actualizar(profesor);
  }
  
  @PatchMapping(path="/actualizarParcial")
  public void actualizarParcial(@RequestBody Profesor profesor){
      this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), profesor.getId());

  }


  @DeleteMapping(path="/borrar/{id}")
  public void borrar(@PathVariable Integer id){
      this.profesorService.borrar(id);
  }
}
