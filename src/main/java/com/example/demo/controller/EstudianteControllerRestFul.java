package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

//METODOS ESTÁTICOS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//API: un api siempre viene determinado por un proyecto

//Servicio-controller: Clase Controller
@RestController//Servicio
//se tiene que crear una URL
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestFul {

    @Autowired
    private IEstudianteService estudianteService;
    
    @Autowired
    private IMateriaService materiaService;
 
    //las capacidades vienen representadas por métdos de una clase
 
   //Path Variable

   
    //GET
    //http://localhost:8080/API/v1.0/Matricula/estudiantes
    //solo las get se pueden consumir desde el navegador
    @GetMapping(path="/{id}",produces = "application/json")//en produces poner el tipo de contenido que se produce
    public ResponseEntity<EstudianteTO> buscar(@PathVariable Integer id){
    	//240: peticiones satisfactorias
    	//240: recurso estudiante encontrado satisfactoriamente
    	//Contrato de la API (documento pdf, swagger.io -> para documentar las APIS)
        EstudianteTO estu = this.estudianteService.buscarto(id);
        
        Link link= linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(estu.getId()))
				.withRel("materias");//se coloca la capacidad donde se encuentra la clase
		estu.add(link);
        
        Link link2= linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(estu.getId()))
				.withSelfRel();
        estu.add(link2);
        
    	return ResponseEntity.status(241).body(estu);
    }

    //CONSULTANDO TODOS LOS ESTUDIANTES
    //http://localhost:8080/API/v1.0/Matricula/estudiantes GET
    @GetMapping(path="/tmp",produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Estudiante>> consultarTodos(@RequestParam(required = false, defaultValue = "masculino") String genero) {
		List<Estudiante> lista = this.estudianteService.buscarTodos(genero);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_242", "Lista consultada de manera satisfactoria.");
		cabeceras.add("mensaje_info", "El sistema va estar en mantenimiento el fin de semana.");
		return new ResponseEntity<>(lista, cabeceras, 242); //todo lo que no es de ka data principal va en al cabecera
        }
    
    
    ///--------------------------------------------------------
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> consultarTodosHateoas() {
		List<EstudianteTO> lista = this.estudianteService.buscarTodosTO();
		
		
		for(EstudianteTO est: lista) {
			Link link= linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(est.getId()))
					.withRel("materias");//se coloca la capacidad donde se encuentra la clase
			est.add(link);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(lista); //todo lo que no es de ka data principal va en al cabecera
    }

    ///tarea10-----------------------
    @GetMapping(path="/estudianteLigero",produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<List<EstudianteLigeroTO>> consultarTodosLigeroHateoas() {
   		List<EstudianteLigeroTO> lista = this.estudianteService.buscarTodosLigeroTO();
   		
   		
   		for(EstudianteLigeroTO est: lista) {
   			Link link= linkTo(methodOn(EstudianteControllerRestFul.class).consultarMateriasPorId(est.getId()))
   					.withSelfRel();//se coloca la capacidad donde se encuentra la clase
   			est.add(link);
   		}
   		
   		return ResponseEntity.status(HttpStatus.OK).body(lista); //todo lo que no es de ka data principal va en al cabecera
       }
    
    //http://localhost:8082/API/v1.0/Matricula/estudiantes/1/materias  GET
    @GetMapping(path="/{id}/materias")
   public ResponseEntity<List<MateriaTO>> consultarMateriasPorId(@PathVariable Integer id){
    	List<MateriaTO> lista = this.materiaService.buscarPorIdEstudiante(id);
    	return ResponseEntity.status(HttpStatus.OK).body(lista);
   }
    
    @PostMapping(consumes =MediaType.APPLICATION_JSON_VALUE)
    public void guardar(@RequestBody EstudianteTO estudiante) {
        this.estudianteService.guardar(estudiante);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizar(@RequestBody EstudianteTO estudiante, @PathVariable Integer id) {
        estudiante.setId(id);
    	this.estudianteService.actualizar(estudiante);
    }
    
    //http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
    @PatchMapping(path="/{id}", consumes = MediaType.APPLICATION_XML_VALUE)//SIEMPRE SE DEBE ESPECIFICAR EXPLICITAMENTE EL TIPO DE CONTENIDO QUE SE VA A MANEJAR, el consumes y el produces, siempre que la capacidad lo requiera
    public void actualizarParcial(@RequestBody EstudianteTO estudiante,  @PathVariable Integer id){
        this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), id);

    }

    
    @DeleteMapping(path="/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public void borrar(@PathVariable Integer id){
        this.estudianteService.borrar(id);
    }
	

 
}