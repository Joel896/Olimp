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
	@JoinColumn(name="DNI", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idServicio", nullable=false)
	private Servicio servicio;
	
	@Column(name="puntoCalificacion", length=10, nullable=false)
	private String punto;
	
	@Column(name="comentarioCalificacion", length=90, nullable=false)
	private String comentario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaCalificacion")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecha;
}
