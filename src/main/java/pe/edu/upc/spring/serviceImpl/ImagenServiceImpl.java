package pe.edu.upc.spring.serviceImpl;

import java.util.ArrayList;
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
	public Optional<Imagen> buscarId(int idImagen) {
		return dImagen.findById(idImagen);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> listar() {
		return dImagen.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> buscarServicio(int idServicio) {
		return dImagen.buscarServicio(idServicio);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> buscarSucursal(int idSucursal) {
		return dImagen.buscarSucursal(idSucursal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Imagen> buscarNombreServicio_Sucursal(String nombreServicio, int idSucursal) {
		return dImagen.buscarNombreServicio_Sucursal(nombreServicio, idSucursal);
	}
	
	public String portadaServicio(int idServicio){
		List<Imagen> lst = (ArrayList<Imagen>)dImagen.buscarServicio(idServicio);
		if(lst.size()>0) {
			if(lst.get(lst.size()-1).getUrl().length()<4) return null;
			else return lst.get(lst.size()-1).getUrl();
		}
		else return null;
	}
}
