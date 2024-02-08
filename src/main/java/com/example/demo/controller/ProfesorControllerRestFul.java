package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.example.demo.repository.modelo.Profesor;

import com.example.demo.service.IProfesorService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
  @GetMapping(path="/{id}",produces = { "application/json", "application/xml" })
  public ResponseEntity<Profesor> buscar(@PathVariable Integer id){
	  
	  Profesor profesor = this.profesorService.buscar(id);
	  return ResponseEntity.status(241).body(profesor);
  }
  

  //Bucar por parametros
@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
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
  @PostMapping(consumes =MediaType.APPLICATION_XML_VALUE)
  public void guardar(@RequestBody Profesor profesor) {
      this.profesorService.guardar(profesor);
  }

  //Actualizar objeto completo : no se coloca el id
  @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
  public void actualizar(@RequestBody Profesor profesor,@PathVariable Integer id) {
	  profesor.setId(id);
      this.profesorService.actualizar(profesor);
  }
  
  //ACTUALIZACIÓN PARCIAL : no se coloca el id
  @PatchMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
  public void actualizarParcial(@RequestBody Profesor profesor, @PathVariable Integer id){
      this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), id);

  }


  @DeleteMapping(path="/{id}")
  public void borrar(@PathVariable Integer id){
      this.profesorService.borrar(id);
  }
}
