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
	
}
