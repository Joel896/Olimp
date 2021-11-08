package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Servicio;

@Repository
public interface IServicioRepository extends JpaRepository<Servicio, Integer>{
	//Busqueda - Filtros
	@Query("from Servicio s where LOWER(s.nombre) like %:nombre%")
	List<Servicio> buscarNombre(@Param("nombre") String nombre);
	
	@Query("from Servicio s where LOWER(s.tipoServicio.nombre) like %:nombre%")
	List<Servicio> buscarTipoServicio(@Param("nombre") String nombre);
	
	@Query("from Servicio s where LOWER(s.sucursal.distrito.nombre) like %:nombre%")
	List<Servicio> buscarDistrito(@Param("nombre") String nombre);
	
	//Panel Sucursal
	@Query("from Servicio s where s.sucursal.idSucursal = :idSucursal")
	List<Servicio> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
	
	@Query("from Servicio s where s.nombre like %:nombre% and s.sucursal.idSucursal = :idSucursal")
	List<Servicio> buscarNombre_Sucursal(@Param("nombre") String nombre, @Param("idSucursal") int idSucursal);
}
