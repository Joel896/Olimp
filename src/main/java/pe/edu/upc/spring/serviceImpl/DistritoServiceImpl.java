package pe.edu.upc.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.repository.IDistritoRepository;
import pe.edu.upc.spring.service.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService{

	@Autowired
	private IDistritoRepository dDistrito;

	@Override
	@Transactional(readOnly = true)
	public List<Distrito> listar() {
		return dDistrito.findAll();
	}

	@Override
	public boolean registrar(Distrito distrito) {
		Distrito objDistrito = dDistrito.save(distrito);
		return objDistrito !=null;
	}

	@Override
	public void eliminar(int idDistrito) {
		dDistrito.deleteById(idDistrito);
	}
}
