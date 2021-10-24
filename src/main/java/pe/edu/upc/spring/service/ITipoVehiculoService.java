package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoVehiculo;

public interface ITipoVehiculoService {
	public boolean registrar(TipoVehiculo tipoVehiculo);
	public void eliminar(int idTipoVehiculo);
	public Optional<TipoVehiculo> listarId(int idTipoVehiculo);
	List<TipoVehiculo> listar();
	List<TipoVehiculo> buscarNombre(String nombre);
}
