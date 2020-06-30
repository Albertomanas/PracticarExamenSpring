package org.elsmancs.practica.controlador;

import java.util.Optional;

import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Usuaria;
import org.elsmancs.practica.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador {

    @Autowired
    Servicio servicio;

    @GetMapping(path="/usuaria/{nombre}", produces={"application/json"})
	@ResponseBody
	public Usuaria recuperaContacte(@PathVariable String nombre) {
        return servicio.getUsuaria(nombre);
    }

    @PostMapping(path="/ordena")
    @ResponseBody
    public String ordena(@RequestParam String usuaria, @RequestParam String item) {
        Optional<Orden> orden = Optional.ofNullable(servicio.ordena(usuaria, item));
        return orden.isPresent()? "OK" : "KO";
    }    
}