package org.elsmancs.practica.repositorio;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

	@PersistenceContext
	private EntityManager em;
}
