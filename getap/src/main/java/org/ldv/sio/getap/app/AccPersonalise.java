package org.ldv.sio.getap.app;

/**
 * type d'accompagnement personnalisé. Ex : "SOS Matière"
 * 
 * 
 * 
 */
public class AccPersonalise {
	private Integer id;
	private String nom;

	public AccPersonalise() {
	}

	public AccPersonalise(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
