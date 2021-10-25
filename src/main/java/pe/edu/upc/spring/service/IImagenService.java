package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Imagen;

public interface IImagenService {
	public boolean registrar(Imagen imagen);
	public void eliminar(int idImagen);
	public Optional<Imagen> listarId(int idImagen);
	List<Imagen> listar();
	List<Imagen> buscarIdServicio(int idServicio);
	List<Imagen> buscarNombreServicio(String nombreServicio);
}
