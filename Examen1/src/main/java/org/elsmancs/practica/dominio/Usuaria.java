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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destreza;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuaria))
			return false;
		Usuaria other = (Usuaria) obj;
		if (destreza != other.destreza)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
