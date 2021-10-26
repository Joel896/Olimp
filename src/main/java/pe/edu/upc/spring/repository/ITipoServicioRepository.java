package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.TipoServicio;

@Repository
public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Integer>{
	//Implementado en JpaRepository
}
