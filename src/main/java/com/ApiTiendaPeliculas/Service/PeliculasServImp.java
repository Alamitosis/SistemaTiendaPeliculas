package com.ApiTiendaPeliculas.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.ApiTiendaPeliculas.Dao.PeliculasDao;
import com.ApiTiendaPeliculas.Entity.Peliculas;

@Service
public class PeliculasServImp {

	@Autowired
	PeliculasDao rep;
	
	
	@Transactional
	public List<Peliculas> listar() {
		return (List<Peliculas>) rep.findAll();
	}
	
	@Transactional
	public Peliculas buscarId(Long id) {
		return rep.findById(id).orElse(null);
	}
	
	@Transactional
	public boolean guardar(Peliculas pelicula) {
		boolean bandera=false;

		for(Peliculas p: rep.findAll()) {
			if(p.getNombre().equals(pelicula.getNombre())) {
				bandera=true;
				break;
			}
		}
		if(bandera==false)
			rep.save(pelicula);
		return bandera;
	}
	// Peticion --- post para eliminar por idProducto
	// Peticion----post para buscar por fechaLanzamiento
		// Peticion----post para buscar por: id, nombre, precio
		// Peticion ---post para eliminar por nombre
	
	@Transactional
	public Boolean eliminarId(Long id) {
		boolean bandera=false;
		
		for(Peliculas p:rep.findAll()) {
			if(p.getIdP().equals(id)) {
				bandera=true;
				rep.deleteById(id);
				break;
			}
		}
		return bandera;
	}
	
	@Transactional(readOnly=true)
	public List<Peliculas> buscarFechaCad(Peliculas pelicula){
		List<Peliculas> listaNuevaFechas= new ArrayList<>();
		
		for(Peliculas p: rep.findAll()) {
			if(p.getFechaLanz().equals(pelicula.getFechaLanz())) {
				listaNuevaFechas.add(p);
			}
		}
		if(listaNuevaFechas.isEmpty())
			return null;
		else
			return listaNuevaFechas;
		
	}
	
	// Peticion----post para buscar por: id, nombre, precio
	@Transactional(readOnly = true)
	public List<Peliculas> buscarIdNomPrecio(@RequestBody Peliculas pelicula) {
		return rep.findByIdPOrNombreOrPrecio(pelicula.getIdP(), pelicula.getNombre(), pelicula.getPrecio());

	}
	
	// Peticion ---post para eliminar por nombre
	@Transactional
	public Boolean eliminarNombre(String nombre) {
		boolean bandera=false;
		
		for(Peliculas p:rep.findAll()) {
			if(p.getNombre().equals(nombre)) {
				rep.deleteByNombre(nombre);
				bandera=true;
				break;
			}
		}
		return bandera;
	}
}
