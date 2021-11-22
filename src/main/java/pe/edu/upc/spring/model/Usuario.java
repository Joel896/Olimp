package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dniUsuario",length=8,unique=true,nullable=false)
	private String dniUsuario;

	@ManyToOne
	@JoinColumn(name="idSucursal", nullable=true)
	private Sucursal sucursal;
	
	@Column(name="nombreUsuario",length=40,nullable=false)
	private String nombre;
	
	@Column(name="correoUsuario",length=50,nullable=false)
	private String correo;
	
	@Column(name="contraseniaUsuario",length=100,nullable=false)
	private String contrasenia;
	
	@Column(name="celularUsuario",length=9,nullable=false)
	private String celular;
	
	private Boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="dniUsuario")
	private List<Rol> roles;
	
	public Usuario() {
		super();
	}

	public Usuario(String dniUsuario, Sucursal sucursal, String nombre, String correo, String contrasenia,
			String celular, Boolean enabled, List<Rol> roles) {
		super();
		this.dniUsuario = dniUsuario;
		this.sucursal = sucursal;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.celular = celular;
		this.enabled = enabled;
		this.roles = roles;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	public String getRol() {
		return roles.get(0).getNombre();
	}
}
