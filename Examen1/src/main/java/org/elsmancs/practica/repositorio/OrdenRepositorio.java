package org.elsmancs.practica.repositorio;

import java.util.List;
import java.util.Optional;

import org.elsmancs.practica.dominio.Orden;
import org.springframework.data.repository.CrudRepository;

public interface OrdenRepositorio  extends CrudRepository<Orden, Long>{

	public Optional<List<Orden>> findByUserNombre(String userName);
}
