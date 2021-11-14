package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.EstadoSolicitud;

public interface IEstadoSolicitudService {
	public boolean registrar(EstadoSolicitud estado);
	public void eliminar(int idEstado);
	public Optional<EstadoSolicitud> buscarId(int idEstado);
	List<EstadoSolicitud> listar();
}
