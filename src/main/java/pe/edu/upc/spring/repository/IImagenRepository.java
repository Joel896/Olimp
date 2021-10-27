package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen,Integer>{
	//Busqueda
	@Query("from Imagen i where i.servicio.idServicio = :idServicio")
	List<Imagen> buscarServicio(@Param("idServicio") int idServicio); //findAll
	
	//Panel sucursal
	@Query("from Imagen i where i.servicio.sucursal.idSucursal = :idSucursal")
	List<Imagen> buscarSucursal(@Param("idSucursal") int idSucursal); //findAll
	
	@Query("from Imagen i where i.servicio.nombre like %:nombre% and i.servicio.sucursal.idSucursal = :idSucursal")
	List<Imagen> buscarNombreServicio_Sucursal(@Param("nombre") String nombre, @Param("idSucursal") int idSucursal);
}
