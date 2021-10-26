package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Tarifa;
import pe.edu.upc.spring.repository.ITarifaRepository;
import pe.edu.upc.spring.service.ITarifaService;

@Service
public class TarifaServiceImpl implements ITarifaService{
	
	@Autowired
	private ITarifaRepository dTarifa;
	
	@Override
	@Transactional
	public boolean registrar(Tarifa tarifario) {
		Tarifa objTarifario = dTarifa.save(tarifario);
		if (objTarifario == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idTarifario) {
		dTarifa.deleteById(idTarifario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Tarifa> buscarId(int idTarifario) {
		return dTarifa.findById(idTarifario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> listar() {
		return dTarifa.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> buscarServicio(int idServicio) {
		return dTarifa.buscarServicio(idServicio);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> buscarSucursal(int idSucursal) {
		return dTarifa.buscarSucursal(idSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> buscarNombreServicio_Sucursal(String nombreServicio, int idSucursal) {
		return dTarifa.buscarNombreServicio_Sucursal(nombreServicio, idSucursal);
	}
}
