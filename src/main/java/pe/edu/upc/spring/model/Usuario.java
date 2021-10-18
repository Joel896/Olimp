package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dniUsuario",length=8,nullable=false)
	private String DNI;
	
	@Column(name="nombreUsuario",length=40,nullable=false)
	private String nombre;
	
	@Column(name="correoUsuario",length=25,nullable=false)
	private String correo;
	
	@Column(name="contraseniaUsuario",length=30,nullable=false)
	private String contrasenia;
	
	@Column(name="celularUsuario",length=9,nullable=false)
	private String celular;
	
	public Usuario() {
		super();
	}

	public Usuario(String dNI, String nombre, String correo, String contrasenia, String celular) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.celular = celular;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
