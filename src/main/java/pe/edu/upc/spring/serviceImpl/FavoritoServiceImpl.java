package pe.edu.upc.spring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Favorito;
import pe.edu.upc.spring.repository.IFavoritoRepository;
import pe.edu.upc.spring.service.IFavoritoService;

@Service
public class FavoritoServiceImpl implements IFavoritoService{

	@Autowired
	private IFavoritoRepository dFavorito;
	
	@Override
	@Transactional
	public boolean registrar(Favorito favorito) {
		Favorito objFavorito = dFavorito.save(favorito);
		if(objFavorito == null) return false;
		else return true;
	}

	@Override
	@Transactional
	public void eliminar(int idFavorito) {
		dFavorito.deleteById(idFavorito);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Favorito> buscarId(int idFavorito) {
		return dFavorito.findById(idFavorito);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Favorito> listar() {
		return dFavorito.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Favorito buscarServicioUsuario(int idServicio, String dniUsuario) {
		List<Favorito> lst = dFavorito.buscarServicioUsuario(dniUsuario, idServicio);
		if(lst.size()>0)return lst.get(0);
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Favorito buscarSucursalUsuario(int idSucursal, String dniUsuario) {
		List<Favorito> lst = dFavorito.buscarSucursalUsuario(dniUsuario, idSucursal);
		if(lst.size()>0)return lst.get(0);
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Favorito> listarServicios(String dniUsuario) {
		return dFavorito.listarServicios(dniUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Favorito> listarSucursales(String dniUsuario) {
		return dFavorito.listarSucursales(dniUsuario);
	}

}
