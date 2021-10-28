package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Distrito;

public interface IDistritoService {
	public boolean registrar(Distrito distrito);
	public void eliminar(int idDistrito);
	List<Distrito> listar();
}
