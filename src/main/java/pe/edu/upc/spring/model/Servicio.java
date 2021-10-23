package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Column(name="Nombre", length=60, nullable=false)
	private String nombre;
	
	@Column(name="Descripcion", length=60, nullable=false)
	private String descripcion;

}
