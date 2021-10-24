package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Distrito")
public class Distrito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDistrito;
	
	@Column(name="nombreDistrito", length=60, nullable = false)
	private String nameDistrito;

	//Generate contructors form superclass

	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Generate contructors using fields

	public Distrito(int idDistrito, String nameDistrito) {
		super();
		this.idDistrito = idDistrito;
		this.nameDistrito = nameDistrito;
	}

	//Generate getters and setters
	
	public int getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNameDistrito() {
		return nameDistrito;
	}

	public void setNameDistrito(String nameDistrito) {
		this.nameDistrito = nameDistrito;
	}

}
