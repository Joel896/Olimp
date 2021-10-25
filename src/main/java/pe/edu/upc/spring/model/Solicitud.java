package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name="Solicitud")
public class Solicitud implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitud;
	
	@ManyToOne
	@JoinColumn(name="dniUsuario",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idServicio",nullable=false)
	private Servicio servicio;
	
	@ManyToOne
	@JoinColumn(name="idEstadoSolicitud",nullable=false)
	private EstadoSolicitud estado;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaCreacionSolicitud")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaAtencionSolicitud")
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	private Date fechaAtencion;
	
	@Column(name="observacionSolicitud",nullable=true,length=50)
	private String observacion;
	
	@Column(name="direccionSolicitud",nullable=true,length=50)
	private String direccion;
	
	public Solicitud() {
		super();
	}

	public Solicitud(int idSolicitud, Usuario usuario,Servicio servicio, EstadoSolicitud estado, Date fechaCreacion, Date fechaAtencion,
			String observacion, String direccion) {
		super();
		this.idSolicitud = idSolicitud;
		this.usuario = usuario;
		this.servicio = servicio;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaAtencion = fechaAtencion;
		this.observacion = observacion;
		this.direccion = direccion;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
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

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
