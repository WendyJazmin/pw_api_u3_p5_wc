package com.example.demo.repository.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="estudiante")
@JsonIgnoreProperties(value="materias")
public class Estudiante {
	

	@Id
	@GeneratedValue(generator="seq_estudiante", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_estudiante", sequenceName="seq_estudiante",allocationSize=1)
	@Column(name="estu_id")
	private Integer id;
	
	
	@Column(name="estu_nombre")
	private String nombre;
	
	@Column(name="estu_apellido")
	private String apellido;
	
	@Column(name="estu_genero")
	private String genero;
	
	@Column(name="estu_fecha_nacimiento")
	//private LocalDateTime fechaNacimiento;
	private LocalDate fechaNacimiento;//LocalDate -> para solo ingressar la fecha, sin la hora parainsertar desde la pagina web
	
	@Column(name="estu_correo_electronico")
	private String correoElectronico;
	
	@Column(name="estu_numero_telefono")
    private String numeroTelefono;
    
	@Column(name="estu_direccion")
    private String direccion;
    
	@Column(name="estu_anio_ingreso")
    private Integer anioIngreso;
    
	@Column(name="estu_carrera")
    private String carrera;

	@OneToMany(mappedBy = "estudiante")
	private List<Materia> materias;
	 
	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
				+ ", fechaNacimiento=" + fechaNacimiento + "]";
	}

	//GET  y  SET
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getAnioIngreso() {
		return anioIngreso;
	}

	public void setAnioIngreso(Integer anioIngreso) {
		this.anioIngreso = anioIngreso;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	
	
	
	

}
