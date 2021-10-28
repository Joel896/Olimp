package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Empresa")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="rucEmpresa",length=11,nullable=false)
	private String rucEmpresa;
	
	@Column(name="nombreEmpresa", length=40, nullable = false)
	private String nombre;
	
	@Column(name="descripcionEmpresa", length=70, nullable = false)
	private String descripcion;
	
	public Empresa() {
		super();
	}

	public Empresa(String rucEmpresa, String nombre, String descripcion) {
		super();
		this.rucEmpresa = rucEmpresa;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getRucEmpresa() {
		return rucEmpresa;
	}

	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
