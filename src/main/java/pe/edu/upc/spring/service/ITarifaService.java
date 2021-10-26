package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Tarifa;

public interface ITarifaService {
	public boolean registrar(Tarifa tarifario);
	public void eliminar(int idTarifario);
	public Optional<Tarifa> buscarId(int idTarifario);
	List<Tarifa> listar();
	List<Tarifa> buscarServicio(int idServicio);
	List<Tarifa> buscarSucursal(int idSucursal);
	List<Tarifa> buscarNombreServicio_Sucursal(String nombreServicio, int idSucursal);
}
