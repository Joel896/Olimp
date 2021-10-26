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

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name="Calificacion")
public class Calificacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCalificacion;
	
	@ManyToOne
	@JoinColumn(name="dniUsuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idServicio", nullable=false)
	private Servicio servicio;
	
	@Column(name="puntosCalificacion", nullable=false)
	private int puntos;
	
	@Column(name="comentarioCalificacion", length=50, nullable=false)
	private String comentario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaCalificacion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;

	public Calificacion(int idCalificacion, Usuario usuario, Servicio servicio, int puntos, String comentario,
			Date fecha) {
		super();
		this.idCalificacion = idCalificacion;
		this.usuario = usuario;
		this.servicio = servicio;
		this.puntos = puntos;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	public int getIdCalificacion() {
		return idCalificacion;
	}

	public void setIdCalificacion(int idCalificacion) {
		this.idCalificacion = idCalificacion;
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

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
