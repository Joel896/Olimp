package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Empresa;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer>{

	@Query("from Empresa e where e.nameEmpresa like %:nameEmpresa%")
	List<Empresa> buscarNombre(@Param("nameEmpresa") String nameEmpresa);
}
