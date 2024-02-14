package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public void guardar(EstudianteTO estudiante) {
		Estudiante estu = this.convertirTOaEstudiante(estudiante);
		
		this.estudianteRepository.insertar(estu);
	}
	
	private Estudiante convertirTOaEstudiante(EstudianteTO estudianteTO) {
		Estudiante estu = new Estudiante();
		estu.setId(estudianteTO.getId());
		estu.setNombre(estudianteTO.getNombre());
		estu.setApellido(estudianteTO.getApellido());
		estu.setGenero(estudianteTO.getGenero());
		estu.setFechaNacimiento(estudianteTO.getFechaNacimiento());
		estu.setAnioIngreso(estudianteTO.getAnioIngreso());
		estu.setDireccion(estudianteTO.getDireccion());
		estu.setCorreoElectronico(estudianteTO.getCorreoElectronico());
		estu.setNumeroTelefono(estudianteTO.getNumeroTelefono());
		estu.setCarrera(estudianteTO.getCarrera());
		return estu;
	}


	@Override
	public void actualizar(EstudianteTO estudiante) {
		Estudiante estu = this.convertirTOaEstudiante(estudiante);
		
		this.estudianteRepository.actualizar(estu);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		
		this.estudianteRepository.actualizarParcial(apellido, nombre, id);
	}

	@Override
	public Estudiante buscar(Integer id) {
		
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public List<Estudiante> buscarTodos(String genero) {

		return this.estudianteRepository.buscarTodos(genero);
	}

	@Override
	public List<EstudianteTO> buscarTodosTO() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRepository.buscarTodos("masculino");
		List<EstudianteTO> listaFinal = new ArrayList<>();
		
		for(Estudiante est: lista) {
			listaFinal.add(this.convertir(est));
		}
		return listaFinal;
	}

	private EstudianteTO convertir(Estudiante est) {
		EstudianteTO estuTO = new EstudianteTO();
		
		estuTO.setApellido(est.getApellido());
		estuTO.setFechaNacimiento(est.getFechaNacimiento());
		estuTO.setGenero(est.getGenero());
		estuTO.setId(est.getId());
		estuTO.setNombre(est.getNombre());
		estuTO.setAnioIngreso(est.getAnioIngreso());
		estuTO.setCarrera(est.getCarrera());
		estuTO.setCorreoElectronico(est.getCorreoElectronico());
		estuTO.setDireccion(est.getDireccion());
		estuTO.setNumeroTelefono(est.getNumeroTelefono());
		
		return estuTO;
	}

	@Override 
	public EstudianteTO buscarto(Integer id) {
		// TODO Auto-generated method stub
		return this.convertir(this.estudianteRepository.seleccionar(id));
	}

	
	private  EstudianteLigeroTO convertirEstudianteLigero(Estudiante estudiante) {
		
		EstudianteLigeroTO estudianteLigero = new EstudianteLigeroTO();
		estudianteLigero.setId(estudiante.getId());
		estudianteLigero.setNombre(estudiante.getNombre());;
		
		
		return estudianteLigero;
	}
	
	@Override
	public List<EstudianteLigeroTO> buscarTodosLigeroTO() {
		List<Estudiante> lista = this.estudianteRepository.buscarTodos("masculino");
		List<EstudianteLigeroTO> listaFinal = new ArrayList<>();
		
		for(Estudiante est: lista) {
			listaFinal.add(this.convertirEstudianteLigero(est));
		}
		return listaFinal;
	}
}
