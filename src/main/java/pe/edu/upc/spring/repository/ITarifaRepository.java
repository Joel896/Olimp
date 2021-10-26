package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Tarifa;


@Repository
public interface ITarifaRepository extends JpaRepository<Tarifa, Integer> {
	@Query("from Tarifa t where t.servicio.idServicio = :idServicio")
	List<Tarifa> buscarServicio(@Param("idServicio") int idServicio); //findAll
	
	@Query("from Tarifa t where t.servicio.sucursal.idSucursal = :idSucursal")
	List<Tarifa> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
	
	@Query("from Tarifa t where t.servicio.nombre like %:nombre% and t.servicio.sucursal.idSucursal = :idSucursal")
	List<Tarifa> buscarNombreServicio_Sucursal(@Param("nombre") String nombre, @Param("idSucursal") int idSucursal);
}