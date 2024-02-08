package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    //http://localhost:8080/API/v1.0/Matricula/estudiantes
    //solo las get se pueden consumir desde el navegador
    @GetMapping(path="/{id}",produces = "application/json")//en produces poner el tipo de contenido que se produce
    public ResponseEntity<Estudiante> buscar(@PathVariable Integer id){
    	//240: peticiones satisfactorias
    	//240: recurso estudiante encontrado satisfactoriamente
    	//Contrato de la API (documento pdf, swagger.io -> para documentar las APIS)
        Estudiante estu = this.estudianteService.buscar(id);
    	return ResponseEntity.status(241).body(estu);
    }

    //CONSULTANDO TODOS LOS ESTUDIANTES
    //http://localhost:8080/API/v1.0/Matricula/estudiantes GET
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Estudiante>> consultarTodos(@RequestParam(required = false, defaultValue = "masculino") String genero) {
		List<Estudiante> lista = this.estudianteService.buscarTodos(genero);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_242", "Lista consultada de manera satisfactoria.");
		cabeceras.add("mensaje_info", "El sistema va estar en mantenimiento el fin de semana.");
		return new ResponseEntity<>(lista, cabeceras, 242); //todo lo que no es de ka data principal va en al cabecera
        }

    
    @PostMapping(consumes =MediaType.APPLICATION_XML_VALUE)
    public void guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
        estudiante.setId(id);
    	this.estudianteService.actualizar(estudiante);
    }
    
    //http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
    @PatchMapping(path="/{id}", consumes = MediaType.APPLICATION_XML_VALUE)//SIEMPRE SE DEBE ESPECIFICAR EXPLICITAMENTE EL TIPO DE CONTENIDO QUE SE VA A MANEJAR, el consumes y el produces, siempre que la capacidad lo requiera
    public void actualizarParcial(@RequestBody Estudiante estudiante,  @PathVariable Integer id){
        this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);

    }

    
    @DeleteMapping(path="/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public void borrar(@PathVariable Integer id){
        this.estudianteService.borrar(id);
    }
	

}