package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Mascota")
public class Sucursal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSucursal;
	
	@Column(name="direccionSucursal", length=60, nullable = false)
	private String direSucursal;
	
	@Column(name="correoSucursal", length=60, nullable = false)
	private String correoSucursal;
	
	@Column(name="celularSucursal", length=60, nullable = false)
	private String celSucursal;

	@Temporal(TemporalType.DATE)
	@Column(name="horarioAtencion")
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date horarioSucursal;
	
	@ManyToOne
	@JoinColumn(name="idEmpresa", nullable=false)
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito distrito;

	//Generate contructors form superclass

	public Sucursal() {
		super();
		// TODO Auto-generated constructor stub
	}


	//Generate contructors using fields	
	
	public Sucursal(int idSucursal, String direSucursal, String correoSucursal, String celSucursal,
			Date horarioSucursal, Empresa empresa, Distrito distrito) {
		super();
		this.idSucursal = idSucursal;
		this.direSucursal = direSucursal;
		this.correoSucursal = correoSucursal;
		this.celSucursal = celSucursal;
		this.horarioSucursal = horarioSucursal;
		this.empresa = empresa;
		this.distrito = distrito;
	}

	//Generate getters and setters

	public int getIdSucursal() {
		return idSucursal;
	}


	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}


	public String getDireSucursal() {
		return direSucursal;
	}


	public void setDireSucursal(String direSucursal) {
		this.direSucursal = direSucursal;
	}


	public String getCorreoSucursal() {
		return correoSucursal;
	}


	public void setCorreoSucursal(String correoSucursal) {
		this.correoSucursal = correoSucursal;
	}


	public String getCelSucursal() {
		return celSucursal;
	}


	public void setCelSucursal(String celSucursal) {
		this.celSucursal = celSucursal;
	}


	public Date getHorarioSucursal() {
		return horarioSucursal;
	}


	public void setHorarioSucursal(Date horarioSucursal) {
		this.horarioSucursal = horarioSucursal;
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
	
	
}
