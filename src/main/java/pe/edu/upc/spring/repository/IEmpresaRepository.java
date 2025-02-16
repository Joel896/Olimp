package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Empresa;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, String>{
	//Implementado en JpaRepository
}
