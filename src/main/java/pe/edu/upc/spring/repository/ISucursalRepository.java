package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Sucursal;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Integer>{

	@Query("from Sucursal s where s.nameSucursal like %:nameSucursal%")
	List<Sucursal> buscarNombre(@Param("nameSucursal") String nameSucursal);
	
	@Query("from Sucursal s where s.direSucursal like %:direSucursal%")
	List<Sucursal> buscarDireccion(@Param("direSucursal") String direSucursal);

	@Query("from Sucursal s where s.celSucursal like %:celSucursal%")
	List<Sucursal> buscarNumero(@Param("celSucursal") String celSucursal);
	
	@Query("from Sucursal s where s.empresa.nameEmpresa like %:nameEmpresa%")
	List<Sucursal> buscarEmpresa(@Param("nameEmpresa") String nameEmpresa);
	
	@Query("from Sucursal s where s.distrito.nameDistrito like %:nameDistrito%")
	List<Sucursal> buscarDistrito(@Param("nameDistrito") String nameDistrito);
	
	List<Sucursal> findhorarioSucursal(Date horarioSucursal);
	
}
