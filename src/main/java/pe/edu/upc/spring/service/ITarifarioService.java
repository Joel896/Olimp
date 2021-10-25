package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Tarifario;

public interface ITarifarioService {
	public boolean Registrar(Tarifario tarifario);
	public void Eliminar(int idTarifario);
	public Optional<Tarifario> ListarId(int idTarifario);
	public Optional<Tarifario> BuscarId(int idTarifario);
	List<Tarifario> Listar();
	List<Tarifario> ListarPorNombreDeServicio(String nombreServicio);
	List<Tarifario> ListarPorIdDeServicio(String idServicio);
}
