package org.elsmancs.practica.repositorio;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	
	/*
	 * 1. Usuaria pueda ordenar varios items a la vez
	 * 2. Guarda las ordenes en la BBDD
	 * 3. No se crea una orden si el usuario no existe en la BBDD y lo mismo para items.
	 */
	@Transactional
	public List<Orden> ordenarMultiple(String cliente, List<String> productos) {
		
		Optional<Usuaria> user = Optional.ofNullable(this.cargaUser(cliente));
		if (user.isEmpty()) {
			// Devuelve una lista inmutable cargada de cargaUser
			return Collections.emptyList();
		}
		
		// Creamos lista mutable para poder realizar transacciones
		List<Orden> ordenes = new ArrayList<Orden>();
		
		/*Inicializar orden a null para poder mutar las propiedades del método ordenar 
		 * y checkear los requisitos
		 */
		Orden orden = null;
		for (String producto: productos) {
			
			try {
				// ordenar es un método anterior que ordena para 1 user, 1 producto
				orden = this.ordenar(user.get().getNombre(), producto);
			} catch (NotEnoughProException e) {
				continue;
			}
			// carga a la bbdd gracias a la lista mutable ordenes si cumple el requisito
			if (orden != null) {
				ordenes.add(orden);
			}
		}
		return ordenes;
		
		
	}
	
}

