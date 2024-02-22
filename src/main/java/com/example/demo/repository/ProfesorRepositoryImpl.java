package com.example.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Profesor;

@Repository
@Transactional
public class ProfesorRepositoryImpl implements IProfesorRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.persist(profesor);
	}

	@Override
	public void actualizar(Profesor profesor) {
		// TODO Auto-generated method stub
		this.entityManager.merge(profesor);
	}

	@Override
	public void actualizarParcial(String apellido, String nombre, Integer id) {
		// TODO Auto-generated method stub

		Query query=this.entityManager
				.createQuery("UPDATE Profesor p SET p.nombre =:valor1, p.apellido =:valor2 WHERE p.id =: valor3");
		query.setParameter("valor1", nombre);
		query.setParameter("valor2", apellido);
		query.setParameter("valor3", id);
		
		query.executeUpdate();
		
	}

	@Override
	public Profesor seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Profesor.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public List<Profesor> buscarMaterias(Integer materias) {
		
		TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE p.materias=:variable", Profesor.class);
		myQuery.setParameter("variable", materias);
		return myQuery.getResultList();
	}

	@Override
	public List<Profesor> buscarTodos(String titulo) {
		
		//System.out.println("Buscando profesores con el título: " + titulo);
		TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE p.titulo_academico=:variable", Profesor.class);
		myQuery.setParameter("variable", titulo);
		return myQuery.getResultList();
	}

	@Override
	public List<Profesor> buscarPorSalario(BigDecimal salario) {
		TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE p.salario=:variable", Profesor.class);
		myQuery.setParameter("variable", salario);
		return myQuery.getResultList();
	}

}
