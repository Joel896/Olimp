package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.repository.IEmpresaRepository;
import pe.edu.upc.spring.service.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

	
	@Autowired
	private IEmpresaRepository eEmpresa;
	
	@Override
	@Transactional
	public boolean registrar(Empresa empresa) {
		Empresa objEmpresa = eEmpresa.save(empresa);
		if (objEmpresa == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Empresa empresa) {
		boolean flag = false;
		try {
			eEmpresa.save(empresa);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un error al modificar");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empresa> listarId(int idEmpresa) {
		return eEmpresa.findById(idEmpresa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> listar() {
		return eEmpresa.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> buscarNombre(String nameEmpresa) {
		return eEmpresa.buscarNombre(nameEmpresa);
	}
	
	

}
