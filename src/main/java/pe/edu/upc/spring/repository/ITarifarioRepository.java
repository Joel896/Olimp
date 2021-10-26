package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Tarifario;


@Repository
public interface ITarifarioRepository extends JpaRepository<Tarifario, Integer> {
	@Query("from Tarifario t where t.servicio.nombreServicio like %:nombreServicio%")
	List<Tarifario> ListarPorNombreDeServicio(@Param("nombreServicio") String nombreServicio);
	
	@Query("from Tarifario t where t.servicio.idServicio like %:idServicio%")
	List<Tarifario> ListarPorIdDeServicio(@Param("idServicio") String idServicio);
}