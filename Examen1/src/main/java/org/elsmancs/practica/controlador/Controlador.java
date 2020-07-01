package org.elsmancs.practica.controlador;

import org.elsmancs.practica.dominio.Usuaria;
import org.elsmancs.practica.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador {

	@Autowired
	Servicio servicio;
	
	@GetMapping(path = "/usuaria/{nombre}", produces = {"application/json"})
	@ResponseBody
	public Usuaria obtenerNombre(@PathVariable String nombre) {
		// getUsuaria es un método creado en servicio para llamarlo sin usar repositorio.cargarUsuario
		return servicio.getUsuaria(nombre);
	}
}
