package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_matricules")
public class Matricula {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mat_id")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "mat_alumne")
	private Alumne alumno;
	
	@OneToOne
	@JoinColumn(name = "mat_curs")
	private Curs curs;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Alumne getAlumne() {
		return this.alumno;
	}
	
	public void setAlumne(Alumne alumno) {
		this.alumno = alumno;
	}
	
	public Curs getCurs() {
		return this.curs;
	}
	
	public void setCurs(Curs curs) {
		this.curs = curs;
	}
}
