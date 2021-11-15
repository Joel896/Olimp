package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoVehiculo;

public interface ITipoVehiculoService {
	public boolean registrar(TipoVehiculo tipo);
	public void eliminar(int idTipo);
	public Optional<TipoVehiculo> buscarId(int idTipo);
	List<TipoVehiculo> listar();
}
