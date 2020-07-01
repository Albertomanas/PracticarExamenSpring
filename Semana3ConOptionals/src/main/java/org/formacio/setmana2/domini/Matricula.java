package org.formacio.setmana2.domini;


public class Matricula {

	private Long id; 
	
	private Alumne alumne;
	
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
