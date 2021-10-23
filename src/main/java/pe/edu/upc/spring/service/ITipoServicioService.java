package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;


import pe.edu.upc.spring.model.TipoServicio;

public interface ITipoServicioService {
	public Optional<TipoServicio> listarId(int idTipoServicio);
	List<TipoServicio> listar();
	List<TipoServicio> buscarNombre(String nombreTipoServicio);
}
