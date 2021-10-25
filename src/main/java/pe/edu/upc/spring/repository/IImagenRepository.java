package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Imagen;

@Repository
public interface IImagenRepository extends JpaRepository<Imagen,Integer>{
	@Query("from Imagen i where i.servicio.idServicio")
	List<Imagen> buscarIdServicio(@Param("idServicio") int idServicio);
	
	@Query("from Imagen i where i.servicio.nombre")
	List<Imagen> buscarNombreServicio(@Param("nombre") String nombre);
}
