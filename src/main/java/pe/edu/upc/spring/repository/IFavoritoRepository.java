package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.spring.model.Favorito;

public interface IFavoritoRepository extends JpaRepository<Favorito, Integer>{
	//Panel usuario
	@Query("from Favorito f where f.sucursal is null and f.usuario.dniUsuario = :dniUsuario")
	List<Favorito> listarServicios(@Param("dniUsuario") String dniUsuario);
	
	@Query("from Favorito f where f.servicio is null and f.usuario.dniUsuario = :dniUsuario")
	List<Favorito> listarSucursales(@Param("dniUsuario") String dniUsuario);
}
