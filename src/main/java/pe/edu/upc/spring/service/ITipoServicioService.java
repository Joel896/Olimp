package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.TipoServicio;

public interface ITipoServicioService {
	public boolean registrar(TipoServicio tipo);
	public void eliminar(int idTipo);
	List<TipoServicio> listar();
}
