package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Servicio;

public interface IServicioService {
	public boolean registrar(Servicio servicio);
	public void eliminar(int idServicio);
	public Optional<Servicio> buscarId(int idServicio);
	public Optional<Servicio> buscarSolicitado(int puesto);
	List<Servicio> listar();
	//Busqueda
	List<Servicio> buscarNombre(String nombreServicio);
	List<Servicio> buscarTipoServicio(String nombreTipoServicio);
	List<Servicio> buscarDistrito(String nombreDistrito);
	//Panel sucursal
	List<Servicio> buscarSucursal(int idSucursal); //findAll
	List<Servicio> buscarNombre_Sucursal(String nombreServicio, int idSucursal);
}


