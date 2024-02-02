package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.repository.modelo.Profesor;


public interface IProfesorRepository {

	public void insertar(Profesor profesor);


	public void actualizar(Profesor profesor);

	
	public void actualizarParcial(String apellido, String nombre, Integer id);

	
	public Profesor seleccionar(Integer id);
	
	public List<Profesor> buscarMaterias(Integer materias);
	
	public List<Profesor> buscarTodos(String titulo);
	
public List<Profesor> buscarPorSalario(BigDecimal salario);
	
	
	public void eliminar(Integer id);
}
