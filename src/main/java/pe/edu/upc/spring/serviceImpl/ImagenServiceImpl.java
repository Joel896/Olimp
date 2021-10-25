package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Imagen;
import pe.edu.upc.spring.repository.IImagenRepository;
import pe.edu.upc.spring.service.IImagenService;

@Service
public class ImagenServiceImpl implements IImagenService{
	
	@Autowired
	private IImagenRepository dImagen;
	
	@Override
	@Transactional
	public boolean registrar(Imagen imagen) {
		Imagen objImagen = dImagen.save(imagen);
		return objImagen!=null;
	}

	@Override
	@Transactional
	public void eliminar(int idImagen) {
		dImagen.deleteById(idImagen);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Imagen> listarId(int idImagen) {
		return dImagen.findById(idImagen);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> listar() {
		return dImagen.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> buscarIdServicio(int idServicio) {
		return dImagen.buscarIdServicio(idServicio);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> buscarNombreServicio(String nombreServicio) {
		return dImagen.buscarNombreServicio(nombreServicio);
	}
	
	
}
