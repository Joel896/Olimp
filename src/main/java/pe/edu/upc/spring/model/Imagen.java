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
@Table(name="Imagen")
public class Imagen implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idImagen;
	
	@ManyToOne
	@JoinColumn(name="idServicio",nullable=false)
	private Servicio servicio;
	
	@Column(name="descripcionImagen",nullable=true,length=20)
	private String descripcion;
	
	@Column(name="urlImagen",nullable=false,length=50)
	private String url;

	public Imagen() {
		super();
	}

	public Imagen(int idImagen, Servicio servicio, String descripcion, String url) {
		super();
		this.idImagen = idImagen;
		this.servicio = servicio;
		this.descripcion = descripcion;
		this.url = url;
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
