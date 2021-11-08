package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Sucursal;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Integer>{
	//Busqueda
	@Query("from Sucursal s where LOWER(s.distrito.nombre) like %:nombre%")
	List<Sucursal> buscarDistrito(@Param("nombre") String nombre);
	@Query("from Sucursal s where LOWER(s.empresa.nombre) like %:nombre%")
	List<Sucursal> buscarEmpresa(@Param("nombre") String nombre);
}
