package org.elsmancs.practica.servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Usuaria;
import org.elsmancs.practica.repositorio.NotEnoughProException;
import org.elsmancs.practica.repositorio.OrdenRepositorio;
import org.elsmancs.practica.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

	@Autowired
	OrdenRepositorio interfaceRepo;
	
	@Autowired
	Repositorio repositorio;
	
	@PersistenceContext
	private EntityManager em;
	

	public List<Orden> listarOrdenesUser(String userName) {
		
		Optional<List<Orden>> ordenes = this.interfaceRepo.findByUserNombre(userName);
		if (ordenes.isPresent()) {
			return ordenes.get();
		}
		return Collections.emptyList();
	}
	
	
	// creado para conservar el MVC
	public Usuaria getUsuaria(String nombre) {
		return repositorio.cargaUser(nombre);
	}
	
	public Orden ordenar(String usuaria, String item) {
		try {
			return repositorio.ordenar(usuaria, item);
		} catch (NotEnoughProException e) {
			return null;
		}
	}
}
