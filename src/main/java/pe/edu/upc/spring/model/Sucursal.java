package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Sucursal")
public class Sucursal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSucursal;
	
	@ManyToOne
	@JoinColumn(name="rucEmpresa", nullable=false)
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito distrito;
	
	@Column(name="direccionSucursal", length=40, nullable = false)
	private String direccion;
	
	@Column(name="correoSucursal", length=25, nullable = false)
	private String correo;
	
	@Column(name="celularSucursal", length=9, nullable = false)
	private String celular;

	@Column(name="horarioSucursal", length=70, nullable = false)
	private String horario;

	public Sucursal() {
		super();
	}

	public Sucursal(int idSucursal, Empresa empresa, Distrito distrito, String direccion, String correo, String celular,
			String horario) {
		super();
		this.idSucursal = idSucursal;
		this.empresa = empresa;
		this.distrito = distrito;
		this.direccion = direccion;
		this.correo = correo;
		this.celular = celular;
		this.horario = horario;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
}
