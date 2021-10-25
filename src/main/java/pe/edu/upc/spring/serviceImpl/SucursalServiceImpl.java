package pe.edu.upc.spring.serviceimpl;

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
	@Transactional
	public boolean modificar(Sucursal sucursal) {
		boolean flag = false;
		try {
			sSucursal.save(sucursal);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sucursal> listarId(int idSucursal) {
		return sSucursal.findById(idSucursal);
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
	public List<Sucursal> buscarNombre(String nameSucursal) {
		return sSucursal.buscarNombre(nameSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarDireccion(String direSucursal) {
		return sSucursal.buscarDireccion(direSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarNumero(String celSucursal) {
		return sSucursal.buscarNumero(celSucursal);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarEmpresa(String nameEmpresa) {
		return sSucursal.buscarEmpresa(nameEmpresa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> buscarDistrito(String nameDistrito) {
		return sSucursal.buscarDistrito(nameDistrito);
	}
}
