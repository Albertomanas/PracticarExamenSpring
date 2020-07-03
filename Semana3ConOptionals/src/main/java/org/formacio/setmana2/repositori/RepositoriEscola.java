package org.formacio.setmana2.repositori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
	
	@Transactional
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
		Matricula matricula = null;
		Optional<Alumne> alumnado = Optional.ofNullable(em.find(Alumne.class, alumne));
		Optional<Curs> curso = Optional.ofNullable(this.carregaCurs(curs));
		
		if (alumnado.isPresent() && curso.isPresent()) {
			if (alumnado.get().getEdat() < curso.get().getEdatMinima())
				throw new EdatIncorrecteException();

		matricula = new Matricula();
		matricula.setAlumne(alumnado.get());
		matricula.setCurs(curso.get());
		em.persist(matricula);	
		}
		return matricula;
	}
		
}
