package org.formacio.setmana2.repositori;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Repository
public class RepositoriEscola {

	@PersistenceContext
	private EntityManager em;
	
	public Curs carregaCurs(String nom) {
		return em.find(Curs.class, nom);
	}
	
	
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
		Matricula matricula = null;
		Optional<Alumne> alumnado = Optional.ofNullable(em.find(Alumne.class, alumne));
		Optional<Curs> curso = Optional.ofNullable(this.carregaCurs(curs));
		
		matricula = new Matricula();
		matricula.setAlumne(alumnado.get());
		matricula.setCurs(curso.get());
		em.persist(matricula);	
		return matricula;
	}
	
}
