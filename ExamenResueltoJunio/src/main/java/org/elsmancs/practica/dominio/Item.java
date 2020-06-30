package org.elsmancs.practica.dominio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_tipo", discriminatorType = DiscriminatorType.STRING)
@Table(name="t_items")
public class Item {
	
	@Id
	@Column(name="item_nom")
	private String nombre;
	
	@Column(name="item_prop")
	private int propiedad;

	@Column(name = "item_tipo", insertable = false, updatable = false)
	private String tipo;
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPropiedad() {
		return this.propiedad;
	}
	public void setPropiedad(int edat) {
		this.propiedad = edat;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + propiedad;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		
		Item other = (Item) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
        } else if (!nombre.equals(other.nombre)) {
                return false;
        }
		return true;
	}	
}