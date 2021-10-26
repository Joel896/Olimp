package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Calificacion;

@Repository
public interface ICalificacionRepository extends JpaRepository<Calificacion, Integer> {
	//Busqueda
	@Query("from Calificacion c where c.servicio.idServicio = :idServicio")
	List<Calificacion> buscarServicio(@Param("idServicio") int idServicio); //findAll
	
	//Panel usuario
	@Query("from Calificacion c where c.usuario.dniUsuario = :dniUsuario")
	List<Calificacion> buscarUsuario(@Param("dniUsuario") String dniUsuario); //findAll
	
	//Panel sucursal
	@Query("from Calificacion c where c.servicio.sucursal.idSucursal = :idSucursal")
	List<Calificacion> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
}