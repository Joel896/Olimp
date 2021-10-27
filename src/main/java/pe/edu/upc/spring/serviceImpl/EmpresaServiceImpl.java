package pe.edu.upc.spring.serviceImpl;

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
	@Transactional(readOnly = true)
	public Optional<Empresa> buscarId(String rucEmpresa) {
		return eEmpresa.findById(rucEmpresa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> listar() {
		return eEmpresa.findAll();
	}
}
