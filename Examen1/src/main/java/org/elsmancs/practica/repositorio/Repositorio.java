package org.elsmancs.practica.repositorio;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.elsmancs.practica.dominio.NormalItem;
import org.elsmancs.practica.dominio.Orden;
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
	
	@Transactional
	public Orden ordenar (String cliente, String producto) throws NotEnoughProException {
		
		Orden orden = null;
		Optional<Usuaria> user = Optional.ofNullable(this.cargaUser(cliente));
		Optional<NormalItem> item = Optional.ofNullable(this.cargaItem(producto));
		
		if (user.isPresent() && item.isPresent()) {
			
			if (user.get().getDestreza() < item.get().getQuality()) 
				throw new NotEnoughProException();
			
			orden = new Orden();
			orden.setUser(user.get());
			orden.setItem(item.get());
			em.persist(orden);
		}
		return orden;
	}
}

