package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoVehiculo;
import pe.edu.upc.spring.repository.ITipoVehiculoRepository;
import pe.edu.upc.spring.service.ITipoVehiculoService;

@Service
public class TipoVehiculoServiceImpl implements ITipoVehiculoService {
	@Autowired
	private ITipoVehiculoRepository dTipoVehiculo;
	
	@Override
	@Transactional
	public boolean registrar(TipoVehiculo tipoVehiculo) {
		TipoVehiculo objTipoVehiculo = dTipoVehiculo.save(tipoVehiculo);
		if (objTipoVehiculo == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idTipoVehiculo) {
		dTipoVehiculo.deleteById(idTipoVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoVehiculo> listarId(int idTipoVehiculo) {
		return dTipoVehiculo.findById(idTipoVehiculo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoVehiculo> listar() {
		return dTipoVehiculo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoVehiculo> buscarNombre(String nombre) {
		return dTipoVehiculo.buscarNombre(nombre);
	}
}
