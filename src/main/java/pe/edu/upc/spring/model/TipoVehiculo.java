package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="TipoVehiculo")
public class TipoVehiculo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoVehiculo;
	
	@Column(name="nombreTipoVehiculo", length=30, nullable=false)
	private String nombre;

	public TipoVehiculo() {
		super();
	}

	public TipoVehiculo(int idTipoVehiculo, String nombre) {
		super();
		this.idTipoVehiculo = idTipoVehiculo;
		this.nombre = nombre;
	}

	public int getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(int idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}