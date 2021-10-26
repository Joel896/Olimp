package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="TipoServicio")
public class TipoServicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoServicio;
	
	@Column(name="nombreTipoServicio", length=30, nullable=false)
	private String nombre;

	public TipoServicio() {
		super();
	}

	public TipoServicio(int idTipoServicio, String nombre) {
		super();
		this.idTipoServicio = idTipoServicio;
		this.nombre = nombre;
	}

	public int getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
