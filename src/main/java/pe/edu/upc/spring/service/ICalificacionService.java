package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Calificacion;

public interface ICalificacionService {
	public boolean registrar(Calificacion calificacion);
	public void eliminar(int idCalificacion);
	public double promedioCalificaciones(String opcion, int id);
	public int contarCalificaciones(String opcion, int id, int puntos);
	public Calificacion buscarUsuarioServicio(String dniUsuario, int idServicio);
	public Optional<Calificacion> buscarId(int idCalificacion);
	List<Calificacion> listar();
	List<Calificacion> buscarServicio(int idServicio);
	List<Calificacion> buscarUsuario(String dniUsuario);
	List<Calificacion> buscarSucursal(int idSucursal);
}
