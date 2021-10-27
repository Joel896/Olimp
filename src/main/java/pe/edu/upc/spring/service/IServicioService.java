package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Servicio;

public interface IServicioService {
	public boolean registrar(Servicio servicio);
	public void eliminar(int idServicio);
	public Optional<Servicio> buscarId(int idServicio);
	List<Servicio> listar();
	//Busqueda
	List<Servicio> buscarNombre(String nombreServicio);
	List<Servicio> buscarTipoServicio(String nombreServicio, int idTipoServicio);
	List<Servicio> buscarDistrito(String nombreServicio, int idDistrito);
	//Panel sucursal
	List<Servicio> buscarSucursal(int idSucursal); //findAll
	List<Servicio> buscarNombre_Sucursal(String nombreServicio, int idSucursal);
}


