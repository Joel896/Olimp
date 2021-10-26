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
	
	@Column(name="nombre", length=60, nullable=false)
	private String nombre;
	
	@Column(name="descripcion", length=60, nullable=false)
	private String descripcion;

}
