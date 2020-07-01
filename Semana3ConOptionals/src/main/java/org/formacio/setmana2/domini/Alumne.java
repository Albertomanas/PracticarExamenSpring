package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_alumnes")
public class Alumne {

	@Id
	@Column(name = "alu_nom")
	private String nombre;
	
	@Column(name = "alu_edat")
	private int edat;
	
	public String getNom() {
		return this.nombre;
	}
	
	public void setNom(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdat() {
		return this.edat;
	}
	
	public void setEdat(int edat) {
		this.edat = edat;
	}
	
}
