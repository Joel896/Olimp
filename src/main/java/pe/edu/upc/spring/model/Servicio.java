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
@Table(name="Servicio")
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idServicio;
	
	@ManyToOne
	@JoinColumn(name="idTipoServicio", nullable=false)
	private TipoServicio tipoServicio;
	
	@ManyToOne
	@JoinColumn(name="idSucursal", nullable=false)
	private Sucursal sucursal;
	
	@Column(name="nombreServicio", length=40, nullable=false)
	private String nombre;
	
	@Column(name="descripcionServicio", length=70, nullable=false)
	private String descripcion;

	public Servicio() {
		super();
	}

	public Servicio(int idServicio, TipoServicio tipoServicio, Sucursal sucursal, String nombre, String descripcion) {
		super();
		this.idServicio = idServicio;
		this.tipoServicio = tipoServicio;
		this.sucursal = sucursal;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
