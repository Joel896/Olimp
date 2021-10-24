package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TipoVehiculo;

@Repository
public interface ITipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer>{
	@Query("from TipoVehiculo tv where tv.nombre like %:nombre%")
	List<TipoVehiculo> buscarNombre(@Param("nombre") String nombre);
}