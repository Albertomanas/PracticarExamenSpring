package org.elsmancs.practica.repositorio;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.elsmancs.practica.dominio.NormalItem;
import org.elsmancs.practica.dominio.Usuaria;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

	@PersistenceContext
	private EntityManager em;
	
	public Usuaria cargaUser(String nombre) {
		return em.find(Usuaria.class, nombre);
	} 
	
	public NormalItem cargaItem(String nombre) {
		return em.find(NormalItem.class, nombre);
	}
}

