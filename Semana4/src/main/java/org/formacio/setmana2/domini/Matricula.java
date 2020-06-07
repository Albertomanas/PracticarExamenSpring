package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_matricules")
public class Matricula {

	@Id
	@Column(name="mat_id")
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name="mat_alumne")
	private Alumne alumne;
	
	@ManyToOne
	@JoinColumn(name="mat_curs")
	private Curs curs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Alumne getAlumne() {
		return alumne;
	}
	public void setAlumne(Alumne alumne) {
		this.alumne = alumne;
	}
	public Curs getCurs() {
		return curs;
	}
	public void setCurs(Curs curs) {
		this.curs = curs;
	}
	
	
}
