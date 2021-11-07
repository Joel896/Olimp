package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Imagen;

public interface IImagenService {
	public boolean registrar(Imagen imagen);
	public void eliminar(int idImagen);
	public String portadaServicio(int idServicio);
	public Optional<Imagen> buscarId(int idImagen);
	List<Imagen> listar();
	//Busqueda
	List<Imagen> buscarServicio(int idServicio); //findAll
	//Panel sucursal
	List<Imagen> buscarSucursal(int idSucursal); //findAll
	List<Imagen> buscarNombreServicio_Sucursal(String nombreServicio, int idSucursal);
}
