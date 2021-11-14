package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.EstadoSolicitud;
import pe.edu.upc.spring.repository.IEstadoSolicitudRepository;
import pe.edu.upc.spring.service.IEstadoSolicitudService;

@Service
public class EstadoSolicitudServiceImpl implements IEstadoSolicitudService{
	
	@Autowired
	private IEstadoSolicitudRepository dEstado;

	@Override
	@Transactional(readOnly=true)
	public List<EstadoSolicitud> listar() {
		return dEstado.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<EstadoSolicitud> buscarId(int idEstado) {
		return dEstado.findById(idEstado);
	}

	@Override
	public boolean registrar(EstadoSolicitud estado) {
		EstadoSolicitud objEstado = dEstado.save(estado);
		return objEstado !=null;
	}

	@Override
	public void eliminar(int idEstado) {
		dEstado.deleteById(idEstado);
	}
}
