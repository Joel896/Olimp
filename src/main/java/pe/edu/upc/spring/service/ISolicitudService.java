package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Solicitud;

public interface ISolicitudService {
	public boolean registrar(Solicitud solicitud);
	public void eliminar(int idSolicitud);
	public Optional<Solicitud> buscarId(int idSolicitud);
	List<Solicitud> listar();
	List<Solicitud> buscarServicio(int idServicio);
	//Panel usuario
	List<Solicitud> buscarUsuario(String dniUsuario); //findAll
	List<Solicitud> buscarNombreServicio_Usuario(String nombre, String dniUsuario);
	//Panel sucursal
	List<Solicitud> buscarSucursal(int idSucursal); //findAll
	List<Solicitud> buscarNombreServicio_Sucursal(String nombre, int idSucursal);
}
