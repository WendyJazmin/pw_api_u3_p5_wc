package com.example.demo.repository.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="profesor")
public class Profesor {
	
	@Id
	@GeneratedValue(generator="seq_profesor", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_profesor", sequenceName="seq_profesor",allocationSize=1)
	@Column(name="prof_id")
	private Integer id;
	
	@Column(name="prof_nombre")
	private String nombre;
	
	@Column(name="prof_apellido")
	private String apellido;
	
	
	@Column(name="prof_titulo")
	private String titulo_academico;
	
	@Column(name="prof_materias")
	private Integer materias;
	
	@Column(name="prof_telefono")
	private String telefono;
	
	@Column(name="prof_direccion")
	private String direccion;

	@Column(name="prof_salario")
	private BigDecimal salario;

	@Override
	public String toString() {
		return "Profesor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", titulo_academico="
				+ titulo_academico + ", materias=" + materias + ", telefono=" + telefono + ", direccion=" + direccion
				+ ", salario=" + salario + "]";
	}

	
	//GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTitulo_academico() {
		return titulo_academico;
	}

	public void setTitulo_academico(String titulo_academico) {
		this.titulo_academico = titulo_academico;
	}

	public Integer getMaterias() {
		return materias;
	}

	public void setMaterias(Integer materias) {
		this.materias = materias;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	
	
}
