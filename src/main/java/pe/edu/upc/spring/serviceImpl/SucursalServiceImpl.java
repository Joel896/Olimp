package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Sucursal;
import pe.edu.upc.spring.repository.ISucursalRepository;
import pe.edu.upc.spring.service.ISucursalService;

@Service
public class SucursalServiceImpl implements ISucursalService{
	
	@Autowired
	private ISucursalRepository sSucursal;
	
	@Override
	@Transactional
	public boolean registrar(Sucursal sucursal) {
		Sucursal objSucursal = sSucursal.save(sucursal);
		if (objSucursal == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sucursal> buscarId(int idSucursal) {
		return sSucursal.findById(idSucursal);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> listar() {
		return sSucursal.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarDistrito(String nameDistrito) {
		return sSucursal.buscarDistrito(nameDistrito);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarEmpresa(String nameEmpresa) {
		return sSucursal.buscarEmpresa(nameEmpresa);
	}
}
