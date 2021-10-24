package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Sucursal;

public interface ISucursalService {

	public boolean registrar(Sucursal sucursal);
	public void modificar(Sucursal sucursal);
	public Optional<Sucursal> listarId(int idSucursal);
	public Optional<Sucursal> buscarId(int idSucursal);
	List<Sucursal> listar();
	List<Sucursal> buscarNombre(String nameSucursal);
	List<Sucursal> buscarDireccion(String direSucursal);
	List<Sucursal> buscarNumero(String celSucursal);
	List<Sucursal> buscarEmpresa(String nameEmpresa);
	List<Sucursal> buscarDistrito(String nameDistrito);
}
