package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Solicitud;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud,Integer>{
	
	@Query("from Solicitud s where s.servicio.idServicio = :idServicio")
	List<Solicitud> buscarServicio(@Param("idServicio") int idServicio); //findAll
	
	//Panel usuario
	@Query("from Solicitud s where s.usuario.dniUsuario = :dniUsuario")
	List<Solicitud> buscarUsuario(@Param("dniUsuario") String dniUsuario); //findAll
	
	@Query("from Solicitud s where s.servicio.nombre like %:nombre% and s.usuario.dniUsuario = :dniUsuario")
	List<Solicitud> buscarNombreServicio_Usuario(@Param("nombre") String nombre, @Param("dniUsuario") String dniUsuario);
	
	//Panel sucursal
	@Query("from Solicitud s where s.servicio.sucursal.idSucursal = :idSucursal")
	List<Solicitud> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
	
	@Query("from Solicitud s where s.servicio.nombre like %:nombre% and s.servicio.sucursal.idSucursal = :idSucursal")
	List<Solicitud> buscarNombreServicio_Sucursal(@Param("nombre") String nombre, @Param("idSucursal") int idSucursal);
}
