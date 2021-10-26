package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Solicitud;

@Repository
public interface ISolicitudRepository extends JpaRepository<Solicitud,Integer>{
	//Panel usuario
	@Query("from Solicitud s where s.usuario.dniUsuario = :dniUsuario")
	List<Solicitud> buscarUsuario(@Param("idUsuario") String dniUsuario); //findAll
	
	@Query("from Solicitud s where s.servicio.nombre like %:nombre% and s.servicio.usuario.dniUsuario = :dniUsuario")
	List<Solicitud> buscarNombreServicio_Usuario(@Param("nombre") String nombre, @Param("dniUsuario") String dniUsuario);
	
	//Panel sucursal
	@Query("from Solicitud s where s.sucursal.idSucursal=%:idSucursal:%")
	List<Solicitud> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
	
	@Query("from Solicitud s where s.servicio.nombre like %:nombre% and s.servicio.idSucursal = %:idSucursal%")
	List<Solicitud> buscarNombreServicio_Sucursal(@Param("nombre") String nombre, @Param("idSucursal") int idSucursal);
}
