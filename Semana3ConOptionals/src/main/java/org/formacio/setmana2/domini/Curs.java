package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_cursos")
public class Curs {

	@Id
	@Column(name = "cur_nom")
	private String nombre;
	
	@Column(name = "cur_edatminima")
	private int edatminima;
	
	public String getNom() {
		return this.nombre;
	}
	
	public void setNom(String nombre) {
		this.nombre =  nombre;
	}
	
	public int getEdatMinima() {
		return this.edatminima;
	}
	
	public void setEdatMinima(int edatminima) {
		this.edatminima = edatminima;
	}
}
