package com.ApiTiendaPeliculas.Dao;

import java.util.List;

import org.hibernate.tool.schema.extract.spi.ExtractionContext.DatabaseObjectAccess;
import org.springframework.data.repository.CrudRepository;

import com.ApiTiendaPeliculas.Entity.Peliculas;

public interface PeliculasDao extends CrudRepository<Peliculas, Long>{

	List<Peliculas> findByIdPOrNombreOrPrecio(Long idP,String nombre,Float precio);
	
	void deleteByNombre(String nombre);
}
