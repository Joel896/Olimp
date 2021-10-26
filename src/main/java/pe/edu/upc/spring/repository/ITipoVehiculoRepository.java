package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TipoVehiculo;

@Repository
public interface ITipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer>{
	//Implementado en JpaRepository
}