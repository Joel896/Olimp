package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Calificacion;

public interface ICalificacionService {
	public boolean registrar(Calificacion calificacion);
	public void eliminar(int idCalificacion);
	public Optional<Calificacion> buscarId(int idCalificacion);
	List<Calificacion> listar();
	List<Calificacion> buscarServicio(int idServicio);
	List<Calificacion> buscarUsuario(String dniUsuario);
	List<Calificacion> buscarSucursal(int idSucursal);
	/*public double obtenerPromedioCalificacion
	{
		List<Calificacion> calificaciones = cService.findAll();
		if(calificaciones.size() == 0)
			return 0;
		int suma = 0;
		for(int i=0; i<calificaciones.size();i++)
		{
			suma += calificaciones.getPuntos();
		}
		return suma/calificaciones.size();
	}*/

}
