package org.elsmancs.practica.servicio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	

}
