package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public void guardar(Estudiante estudiante) {
		
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		
		this.estudianteRepository.actualizar(estudiante);
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

}
