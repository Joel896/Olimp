package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Sucursal;

public interface ISucursalService {
	public boolean registrar(Sucursal sucursal);
	public Optional<Sucursal> buscarId(int idSucursal);
	List<Sucursal> listar();
	List<Sucursal> buscarDistrito(String nombreDistrito);
	List<Sucursal> buscarEmpresa(String nombreEmpresa);
}
