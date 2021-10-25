package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.EstadoSolicitud;

@Repository
public interface IEstadoSolicitudRepository extends JpaRepository<EstadoSolicitud,Integer>{
	//Implementado en JpaRepository
}
