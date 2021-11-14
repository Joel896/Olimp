package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Empresa;

public interface IEmpresaService {
	public boolean registrar(Empresa empresa);
	public void eliminar(String rucEmpresa);
	public Optional<Empresa> buscarId(String rucEmpresa);
	public List<Empresa> listar();
}
