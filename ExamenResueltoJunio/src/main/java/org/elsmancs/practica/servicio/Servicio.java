package org.elsmancs.practica.servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Usuaria;
import org.elsmancs.practica.repositorio.NotEnoughProException;
import org.elsmancs.practica.repositorio.OrdenRepository;
import org.elsmancs.practica.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    @Autowired
    private OrdenRepository repoCRUD;

    @Autowired
    private Repositorio repo;

    public List<Orden> listarOrdenesUser(String userName) {

        Optional<List<Orden>> ordenes = this.repoCRUD.findByUserNombre(userName);
        return ordenes.isPresent()? ordenes.get() : Collections.emptyList();
    }    

    public Usuaria getUsuaria(String nombre) {
        return repo.cargaUser(nombre);
    }

    @Transactional
    public Orden ordena(String usuaria, String item) {
        try {
            return repo.ordenar(usuaria, item);
        } catch (NotEnoughProException e) {
            return null;
        }
    }
}