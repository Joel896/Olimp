package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="Favorito")
public class Favorito implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFavorito;
	
	@ManyToOne
	@JoinColumn(name="dniUsuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idServicio", nullable=true)
	private Servicio servicio;
	
	@ManyToOne
	@JoinColumn(name="idSucursal", nullable=true)
	private Sucursal sucursal;

	public Favorito() {
		super();
	}

	public Favorito(int idFavorito, Usuario usuario, Servicio servicio, Sucursal sucursal) {
		super();
		this.idFavorito = idFavorito;
		this.usuario = usuario;
		this.servicio = servicio;
		this.sucursal = sucursal;
	}

	public int getIdFavorito() {
		return idFavorito;
	}

	public void setIdFavorito(int idFavorito) {
		this.idFavorito = idFavorito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
