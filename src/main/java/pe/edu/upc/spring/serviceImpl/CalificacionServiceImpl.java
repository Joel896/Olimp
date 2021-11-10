package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Calificacion;
import pe.edu.upc.spring.repository.ICalificacionRepository;
import pe.edu.upc.spring.service.ICalificacionService;

@Service
public class CalificacionServiceImpl implements ICalificacionService{
	
	@Autowired
	private ICalificacionRepository dCalificacion;
	
	@Override
	@Transactional
	public boolean registrar(Calificacion calificacion) {
		Calificacion objCalificacion = dCalificacion.save(calificacion);
		if (objCalificacion == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idCalificacion) {
		dCalificacion.deleteById(idCalificacion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Calificacion> buscarId(int idCalificacion) {
		return dCalificacion.findById(idCalificacion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Calificacion> listar() {
		return dCalificacion.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Calificacion> buscarServicio(int idServicio) {
		return dCalificacion.buscarServicio(idServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Calificacion> buscarUsuario(String idSucursal) {
		return dCalificacion.buscarUsuario(idSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Calificacion> buscarSucursal(int idSucursal) {
		return dCalificacion.buscarSucursal(idSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public double promedioCalificaciones(String opcion, int id) {
		List<Calificacion>lst;
		double resultado = 0;
		if (opcion == "sucursal") lst = dCalificacion.buscarSucursal(id);
		else lst = dCalificacion.buscarServicio(id);
		
		if(lst.size()>0) {
			for(int i=0; i < lst.size(); i++) resultado+=lst.get(i).getPuntos();
			resultado = resultado / lst.size();
		}
		
		return resultado;
	}
	@Override
	@Transactional(readOnly = true)
	public int contarCalificaciones(String opcion, int id, int puntos) {
		List<Calificacion>lst;
		int resultado = 0;
		if (opcion == "sucursal") lst = dCalificacion.buscarSucursal(id);
		else lst = dCalificacion.buscarServicio(id);
		
		for(int i=0; i < lst.size(); i++)
			if(lst.get(i).getPuntos()==puntos) resultado++;
		
		return resultado;
	}
}
