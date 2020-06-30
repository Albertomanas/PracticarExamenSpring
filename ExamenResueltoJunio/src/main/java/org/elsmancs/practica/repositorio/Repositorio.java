package org.elsmancs.practica.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Item;
import org.elsmancs.practica.dominio.Usuaria;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio {

    @PersistenceContext
    private EntityManager em;

    public Usuaria cargaUser(String nombre) {
       return em.find(Usuaria.class, nombre);
    }

    public Item cargaItem(String nombre) {
        return em.find(Item.class, nombre);       
    }

    public Orden ordenar(String cliente, String producto) throws NotEnoughProException {

        Orden orden = null;
        Optional<Usuaria> user = Optional.ofNullable(this.cargaUser(cliente));
        Optional<Item> item  = Optional.ofNullable(this.cargaItem(producto));

        if (user.isPresent() && item.isPresent()) {

            if (user.get().getDestreza() < item.get().getPropiedad())
                throw new NotEnoughProException();
            
            orden = new Orden();
            orden.setUser(user.get());
            orden.setItem(item.get());
            em.persist(orden);         
        }
        return orden;        
    }

    public List<Orden> ordenarMultiple(String cliente, List<String> productos) {

        Optional<Usuaria> user = Optional.ofNullable(this.cargaUser(cliente));
        if (user.isEmpty()) {
            return Collections.emptyList();
        }
        
        List<Orden> ordenes = new ArrayList<Orden>();

        Orden orden = null;
        for (String producto: productos) {

            try {
                orden = this.ordenar(user.get().getNombre(), producto);
            } catch (NotEnoughProException e) {
                continue;
            }

            if (orden != null) {
                ordenes.add(orden);
            }   
        }
        return ordenes;      
    }
}
