package org.elsmancs.practica.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_users")
public class Usuaria {

	@Id
	@Column(name = "user_nom")
	private String nombre;
	
	@Column(name = "user_prop")
	private int destreza;
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getDestreza() {
		return this.destreza;
	}
	
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}
}
