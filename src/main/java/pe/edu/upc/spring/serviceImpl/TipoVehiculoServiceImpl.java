package pe.edu.upc.spring.serviceImpl;

import java.util.List;

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
	@Transactional(readOnly = true)
	public List<TipoVehiculo> listar() {
		return dTipoVehiculo.findAll();
	}
}
