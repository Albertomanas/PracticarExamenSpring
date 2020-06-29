package org.elsmancs.practica.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_items")
public class Item {
	
	@Id
	@Column(name = "item_nom")
	private String nombre;
	
	@Column(name = "item_prop")
	private int propiedad;
	
	@Column(name = "item_tipo")
	private String tipo;
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getPropiedad(){
		return this.propiedad;
	}
	
	public void setPropiedad(int propiedad) {
		this.propiedad = propiedad;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
