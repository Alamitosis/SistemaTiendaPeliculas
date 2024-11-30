package com.ApiTiendaPeliculas.WebService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiTiendaPeliculas.Entity.Peliculas;
import com.ApiTiendaPeliculas.Service.PeliculasServImp;

@RestController
@RequestMapping(path="PeliculasWebService")
@CrossOrigin
public class PeliculasWebService {

	@Autowired
	PeliculasServImp imp;
	
	//http://localhost:9000/PeliculasWebService/listar
	@GetMapping(path="listar")
	public List<Peliculas> listar() {
		return imp.listar();
	}
	
	//http://localhost:9000/PeliculasWebService/buscarId
	@PostMapping(path="buscarId")
	public Peliculas buscarId(@RequestBody Peliculas pelicula) {
		return imp.buscarId(pelicula.getIdP());
	}
	
	//http://localhost:9000/PeliculasWebService/guardar
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula){
		boolean response=imp.guardar(pelicula);
		
		if(response==true) {
			return new ResponseEntity<>("Este producto ya existe",HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(pelicula,HttpStatus.CREATED);

	}
	// Peticion --- post para eliminar por idProducto
		// Peticion----post para buscar por fechaCaducidad
		// Peticion----post para buscar por: id, nombre, precio
		// Peticion ---post para eliminar por nombre
	
	//http://localhost:9000/PeliculasWebService/eliminarId
	@PostMapping(path="eliminarId")
	public ResponseEntity<?> eliminar(@RequestBody Peliculas pelicula) {
		if(imp.eliminarId(pelicula.getIdP())==true) {
			return new ResponseEntity<>("Eliminacion exitosa",HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Eliminacion fallida, no existe ese id",HttpStatus.OK);
	}
	
	//http://localhost:9000/PeliculasWebService/buscarFechaCad
	@PostMapping(path="buscarFechaCad")
	public ResponseEntity<?> buscarFechaCad(@RequestBody Peliculas pelicula) {
		if(imp.buscarFechaCad(pelicula)==null) {
			return new ResponseEntity<>("No hay registros de esa fecha de lanzamiento",HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(imp.buscarFechaCad(pelicula),HttpStatus.CREATED);
	}
	
	//http://localhost:9000/PeliculasWebService/buscarIdNomPrecio
	@PostMapping(path="buscarIdNomPrecio")
	public ResponseEntity<?> buscarIdNomPrecio(@RequestBody Peliculas pelicula){
		
		if(imp.buscarIdNomPrecio(pelicula).isEmpty())
			return new ResponseEntity<> ("No se encontró esta película",HttpStatus.OK);
		else
			return new ResponseEntity<> (imp.buscarIdNomPrecio(pelicula),HttpStatus.CREATED);
	}
	// Peticion ---post para eliminar por nombre
	//http://localhost:9000/PeliculasWebService/eliminarNombre
	@PostMapping(path="eliminarNombre")
	public ResponseEntity<?> eliminarNombre(String nombre){
		boolean respuesta=imp.eliminarNombre(nombre);
		if(respuesta==false) {
			return new ResponseEntity<> ("No se encontró esta película",HttpStatus.OK);
		}
		else
			return new ResponseEntity<> ("Pelicula eliminada con exito",HttpStatus.OK);
	}
}
