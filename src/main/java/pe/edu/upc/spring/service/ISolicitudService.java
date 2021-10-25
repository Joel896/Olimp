package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Solicitud;

public interface ISolicitudService {
	public boolean registrar(Solicitud solicitud);
	public void eliminar(int idSolicitud);
	public Optional<Solicitud> listarId(int idSolicitud);
	List<Solicitud> buscarUsuario(String dniUsuario);
	List<Solicitud> buscarSucursal(int idSucursal);
	List<Solicitud> buscarNombreServicio_Sucursal(String nombre, int idSucursal);
}
