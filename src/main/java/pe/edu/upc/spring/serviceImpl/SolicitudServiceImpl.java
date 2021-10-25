package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Solicitud;
import pe.edu.upc.spring.repository.ISolicitudRepository;
import pe.edu.upc.spring.service.ISolicitudService;

@Service
public class SolicitudServiceImpl implements ISolicitudService {
	
	@Autowired
	private ISolicitudRepository dSolicitud;

	@Override
	@Transactional
	public boolean registrar(Solicitud solicitud) {
		Solicitud objSolicitud = dSolicitud.save(solicitud);
		return objSolicitud!=null;
	}

	@Override
	@Transactional
	public void eliminar(int idSolicitud) {
		dSolicitud.deleteById(idSolicitud);
	}

	@Override
	public Optional<Solicitud> listarId(int idSolicitud) {
		return dSolicitud.findById(idSolicitud);
	}

	@Override
	public List<Solicitud> buscarUsuario(String dniUsuario) {
		return dSolicitud.buscarUsuario(dniUsuario);
	}

	@Override
	public List<Solicitud> buscarSucursal(int idSucursal) {
		return dSolicitud.buscarSucursal(idSucursal);
	}

	@Override
	public List<Solicitud> buscarNombreServicio_Sucursal(String nombre, int idSucursal) {
		return dSolicitud.buscarNombreServicio_Sucursal(nombre, idSucursal);
	}
	
	
}
