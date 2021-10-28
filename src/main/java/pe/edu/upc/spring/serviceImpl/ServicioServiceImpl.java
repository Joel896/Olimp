package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.repository.IServicioRepository;
import pe.edu.upc.spring.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioRepository dServicio;
	
	@Override
	@Transactional
	public boolean registrar(Servicio servicio) {
		Servicio objServicio = dServicio.save(servicio);
		if(objServicio == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idServicio) {
		dServicio.deleteById(idServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Servicio> buscarId(int idServicio) {
		return dServicio.findById(idServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> listar() {
		return dServicio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarNombre(String nombreServicio) {
		return dServicio.buscarNombre(nombreServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarTipoServicio(String nombreTipoServicio) {
		return dServicio.buscarTipoServicio(nombreTipoServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarDistrito(String nombreDistrito) {
		return dServicio.buscarDistrito(nombreDistrito);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarSucursal(int idSucursal) {
		return dServicio.buscarSucursal(idSucursal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarNombre_Sucursal(String nombreServicio, int idSucursal) {
		return dServicio.buscarNombre_Sucursal(nombreServicio, idSucursal);
	}

}

