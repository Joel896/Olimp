package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="EstadoSolicitud")
public class EstadoSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEstadoSolicitud;
	
	@Column(name="nombreEstado",length=20,nullable=false)
	private String nombre;
	
	public EstadoSolicitud() {
		super();
	}

	public EstadoSolicitud(int idEstadoSolicitud, String nombre) {
		super();
		this.idEstadoSolicitud = idEstadoSolicitud;
		this.nombre = nombre;
	}

	public int getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}

	public void setIdEstadoSolicitud(int idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
