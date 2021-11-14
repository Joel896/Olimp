package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Usuario;

public interface IUsuarioService {
	public boolean registrar(Usuario usuario);
	public void eliminar(String dniUsuario);
	public Optional<Usuario> buscarId(String dniUsuario);
	List<Usuario> listar();
}
