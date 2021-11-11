package pe.edu.upc.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Servicio;
import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.repository.ISolicitudRepository;
import pe.edu.upc.spring.repository.ISucursalRepository;
import pe.edu.upc.spring.service.ISucursalService;

@Service
public class SucursalServiceImpl implements ISucursalService{
	
	@Autowired
	private ISucursalRepository dSucursal;
	@Autowired
	private ISolicitudRepository dSolicitud; 
	
	@Override
	@Transactional
	public boolean registrar(Sucursal sucursal) {
		Sucursal objSucursal = dSucursal.save(sucursal);
		if (objSucursal == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sucursal> buscarId(int idSucursal) {
		return dSucursal.findById(idSucursal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> listar() {
		return dSucursal.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarDistrito(String nameDistrito) {
		return dSucursal.buscarDistrito(nameDistrito.toLowerCase());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarEmpresa(String nameEmpresa) {
		return dSucursal.buscarEmpresa(nameEmpresa.toLowerCase());
	}

	@Override
	public Optional<Sucursal> buscarSolicitado(int puesto) {
		List<Sucursal> lst = (ArrayList<Sucursal>)dSucursal.findAll();
		int id, m1=0, m2=0, mayor=0, id_mayor=0;
		for(int i=0; i<lst.size(); i++) {
			id = lst.get(i).getIdSucursal();
			if(dSolicitud.buscarSucursal(id).size()>mayor) {
				mayor=dSolicitud.buscarServicio(id).size();
				id_mayor=id;
			}
		}
		System.out.println("1: " + id_mayor);
		if(puesto > 1) {
			mayor=0; m1=id_mayor;
			for(int i=0; i<lst.size(); i++) {
				id = lst.get(i).getIdSucursal();
				if(dSolicitud.buscarSucursal(id).size()>mayor && id != m1) {
					mayor=dSolicitud.buscarSucursal(id).size();
					id_mayor=id;
				}
			}
		}
		if(puesto > 2) {
			mayor=0; m2=id_mayor;
			for(int i=0; i<lst.size(); i++) {
				id = lst.get(i).getIdSucursal();
				if(dSolicitud.buscarSucursal(id).size()>mayor && id != m1 && id != m2) {
					mayor=dSolicitud.buscarSucursal(id).size();
					id_mayor=id;
				}
			}
		}
		return dSucursal.findById(id_mayor);
	}
}
