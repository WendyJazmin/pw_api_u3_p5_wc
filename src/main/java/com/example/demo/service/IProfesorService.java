package com.example.demo.service;


import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.modelo.Profesor;

public interface IProfesorService {
	
	public void guardar(Profesor profesor);
	
	public void actualizar(Profesor profesor);

	public void actualizarParcial(String apellido, String nombre, Integer id);
	
	public Profesor buscar(Integer id);
	
	public List<Profesor> buscarMaterias(Integer materias);
	
	public List<Profesor> buscarTodos(String titulo);
	
	public List<Profesor> buscarPorSalario(BigDecimal salario);
	public void borrar(Integer id);

}
