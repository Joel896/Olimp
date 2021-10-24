package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Empresa")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpresa;
	
	@Column(name="nombreEmpresa", length=60, nullable = false)
	private String nameEmpresa;
	
	@Column(name="descripcionEmpresa", length=120, nullable = false)
	private String desEmpresa;

	//Generate contructors form superclass
	
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Generate contructors using fields

	public Empresa(int idEmpresa, String nameEmpresa, String desEmpresa) {
		super();
		this.idEmpresa = idEmpresa;
		this.nameEmpresa = nameEmpresa;
		this.desEmpresa = desEmpresa;
	}
	

	//Generate getters and setters

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNameEmpresa() {
		return nameEmpresa;
	}

	public void setNameEmpresa(String nameEmpresa) {
		this.nameEmpresa = nameEmpresa;
	}

	public String getDesEmpresa() {
		return desEmpresa;
	}

	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}


	
}
