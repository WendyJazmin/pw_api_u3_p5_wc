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
  //http://localhost:8080/API/v1.0/Matricula/profesores/GET
  //solo las get se pueden consumir desde el navegador
  @GetMapping(path="/{id}")
  public Profesor buscar(@PathVariable Integer id){
     
      return this.profesorService.buscar(id);
  }
  

  //Bucar por parametros
@GetMapping
public List<Profesor> buscarPorParametros(@RequestParam(required = false) Integer materias,
                                    @RequestParam(required = false) String titulo,
                                    @RequestParam(required = false) BigDecimal salario) {
	  if (materias != null) {
	        return this.profesorService.buscarMaterias(materias);
	    } else if(titulo !=null) {
	    	//System.out.println("Buscando profesores con el título: " + titulo);
	        return this.profesorService.buscarTodos(titulo);
	    }else {
	    	return this.profesorService.buscarPorSalario(salario);
	    }
}


  //GUARDAR
  @PostMapping
  public void guardar(@RequestBody Profesor profesor) {
      this.profesorService.guardar(profesor);
  }

  //Actualizar objeto completo : no se coloca el id
  @PutMapping(path="/{id}")
  public void actualizar(@RequestBody Profesor profesor,@PathVariable Integer id) {
	  profesor.setId(id);
      this.profesorService.actualizar(profesor);
  }
  
  //ACTUALIZACIÓN PARCIAL : no se coloca el id
  @PatchMapping(path="/{id}")
  public void actualizarParcial(@RequestBody Profesor profesor, @PathVariable Integer id){
      this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), id);

  }


  @DeleteMapping(path="/{id}")
  public void borrar(@PathVariable Integer id){
      this.profesorService.borrar(id);
  }
}
