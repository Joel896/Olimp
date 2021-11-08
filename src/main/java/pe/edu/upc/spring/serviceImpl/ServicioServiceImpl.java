package pe.edu.upc.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.repository.IServicioRepository;
import pe.edu.upc.spring.repository.ISolicitudRepository;
import pe.edu.upc.spring.service.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioRepository dServicio;
	@Autowired
	private ISolicitudRepository dSolicitud;
	
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
		return dServicio.buscarNombre(nombreServicio.toLowerCase());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarTipoServicio(String nombreTipoServicio) {
		return dServicio.buscarTipoServicio(nombreTipoServicio.toLowerCase());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Servicio> buscarDistrito(String nombreDistrito) {
		return dServicio.buscarDistrito(nombreDistrito.toLowerCase());
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

	@Override
	@Transactional(readOnly = true)
	public Optional<Servicio> buscarSolicitado(int puesto) {
		List<Servicio> lst = (ArrayList<Servicio>)dServicio.findAll();
		int id, m1=0, m2=0, mayor=0, id_mayor=0;
		for(int i=0; i<lst.size(); i++) {
			id = lst.get(i).getIdServicio();
			if(dSolicitud.buscarServicio(id).size()>mayor) {
				mayor=dSolicitud.buscarServicio(id).size();
				id_mayor=id;
			}
		}
		if(puesto > 1) {
			mayor=0; m1=id_mayor;
			for(int i=0; i<lst.size(); i++) {
				id = lst.get(i).getIdServicio();
				if(dSolicitud.buscarServicio(id).size()>mayor && id != m1) {
					mayor=dSolicitud.buscarServicio(id).size();
					id_mayor=id;
				}
			}
		}
		if(puesto > 2) {
			mayor=0; m2=id_mayor;
			for(int i=0; i<lst.size(); i++) {
				id = lst.get(i).getIdServicio();
				if(dSolicitud.buscarServicio(id).size()>mayor && id != m1 && id != m2) {
					mayor=dSolicitud.buscarServicio(id).size();
					id_mayor=id;
				}
			}
		}
		return dServicio.findById(id_mayor);
	}

}

