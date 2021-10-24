package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Tarifario;

@Repository
public interface ITarifarioRepository extends JpaRepository<Tarifario, Integer>{

}