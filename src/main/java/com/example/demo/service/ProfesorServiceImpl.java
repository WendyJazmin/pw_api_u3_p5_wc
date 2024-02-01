package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProfesorRepository;
import com.example.demo.repository.modelo.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService{

	@Autowired
	private IProfesorRepository profesorRepository;
	
	@Override
	public void guardar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.profesorRepository.insertar(profesor);
	}

	@Override
	public void actualizar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.profesorRepository.actualizar(profesor);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub
		this.profesorRepository.actualizarParcial(apellido, nombre, id);
	}

	@Override
	public Profesor buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.profesorRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.profesorRepository.eliminar(id);
	}

	@Override
	public List<Profesor> buscarMaterias(Integer materias) {
		// TODO Auto-generated method stub
		return this.profesorRepository.buscarMaterias(materias);
	}

	@Override
	public List<Profesor> buscarTodos(String titulo) {
		// TODO Auto-generated method stub
		return this.profesorRepository.buscarTodos(titulo);
	}

}
