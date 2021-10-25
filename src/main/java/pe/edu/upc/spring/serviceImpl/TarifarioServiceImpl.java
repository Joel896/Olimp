package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Tarifario;
import pe.edu.upc.spring.repository.ITarifarioRepository;
import pe.edu.upc.spring.service.ITarifarioService;

public class TarifarioServiceImpl implements ITarifarioService{
	
	@Autowired
	private ITarifarioRepository dTarifario;
	
	@Override
	@Transactional
	public boolean Registrar(Tarifario tarifario) {
		Tarifario objTarifario = dTarifario.save(tarifario);
		if (objTarifario == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void Eliminar(int idTarifario) {
		dTarifario.deleteById(idTarifario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarifario> ListarId(int idTarifario) {
		return dTarifario.findById(idTarifario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Tarifario> BuscarId(int idTarifario) {
		return dTarifario.findById(idTarifario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifario> Listar() {
		return dTarifario.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarifario> ListarPorNombreDeServicio(String nombreServicio) {
		return dTarifario.ListarPorNombreDeServicio(nombreServicio);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifario> ListarPorIdDeServicio(String idServicio) {
		return dTarifario.ListarPorIdDeServicio(idServicio);
	}	
}
