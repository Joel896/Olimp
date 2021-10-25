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
	private IServicioService dServicio;
	
	@Override
	@Transactional
	public boolean insertar(Servicio servicio) {
		Servicio objServicio = dServicio.save(servicio);
		if(objServicio == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Servicio servicio) {
		boolean flag = false;
		try {
			dServicio.save(servicio);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idServicio) {
		dServicio.deleteById(idServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Servicio> listarId(int idServicio) {
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

}

