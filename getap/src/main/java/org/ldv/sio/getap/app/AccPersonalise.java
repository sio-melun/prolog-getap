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
	private int origineEtat;
	private int idUser;

	public AccPersonalise() {
	}

	public AccPersonalise(Integer id, String nom, int origineEtat, int idUser) {
		super();
		this.id = id;
		this.nom = nom;
		this.origineEtat = origineEtat;
		this.idUser = idUser;
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

	public int getOrigineEtat() {
		return origineEtat;
	}

	public void setOrigineEtat(int origineEtat) {
		this.origineEtat = origineEtat;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
