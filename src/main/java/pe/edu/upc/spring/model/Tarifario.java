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
@Table(name="Tarifario")
public class Tarifario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarifario;
	
	@ManyToOne
	@JoinColumn(name="idTipoVehiculo", nullable=false)
	private TipoVehiculo tipoVehiculo;
	
	@ManyToOne
	@JoinColumn(name="idServicio", nullable=false)
	private Servicio servicio;
	
	@Column(name="precioTarifario", length=60, nullable=false)
	private String precio;

	public Tarifario() {
		super();
	}

	public Tarifario(int idTarifario, TipoVehiculo tipoVehiculo, Servicio servicio, String precio) {
		super();
		this.idTarifario = idTarifario;
		this.tipoVehiculo = tipoVehiculo;
		this.servicio = servicio;
		this.precio = precio;
	}

	public int getIdTarifario() {
		return idTarifario;
	}

	public void setIdTarifario(int idTarifario) {
		this.idTarifario = idTarifario;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}
}
