package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.EstadoSolicitud;

public interface IEstadoSolicitudService {
	public boolean registrar(EstadoSolicitud estado);
	public void eliminar(int idEstado);
	List<EstadoSolicitud> listar();
}
