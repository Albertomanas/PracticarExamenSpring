package org.elsmancs.practica.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_ordenes")
public class Orden {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ord_id")
    private Long id; 
    
    @OneToOne
	@JoinColumn(name="ord_user")
	private Usuaria user;
	
	@OneToOne
	@JoinColumn(name="ord_item")
	private Item item;	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Usuaria getUser() {
		return this.user;
	}
	public void setUser(Usuaria user) {
		this.user = user;
	}	
}