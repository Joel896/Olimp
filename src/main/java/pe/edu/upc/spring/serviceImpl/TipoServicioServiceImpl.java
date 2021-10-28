package pe.edu.upc.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoServicio;
import pe.edu.upc.spring.repository.ITipoServicioRepository;
import pe.edu.upc.spring.service.ITipoServicioService;

@Service
public class TipoServicioServiceImpl implements ITipoServicioService{

	@Autowired
	private ITipoServicioRepository dTipo;
	
	@Override
	@Transactional(readOnly=true)
	public List<TipoServicio> listar() {
		return dTipo.findAll();
	}

	@Override
	@Transactional
	public boolean registrar(TipoServicio tipo) {
		TipoServicio objTipo = dTipo.save(tipo);
		return objTipo !=null;
	}

	@Override
	@Transactional
	public void eliminar(int idTipo) {
		dTipo.deleteById(idTipo);
	}
}
