package org.elsmancs.practica.dominio;

import javax.persistence.Entity;


@Entity
public class NormalItem extends Item{
	
	public int getQuality() {
		return this.getPropiedad();
	}

}
