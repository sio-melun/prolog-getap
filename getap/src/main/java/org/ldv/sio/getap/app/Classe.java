package org.ldv.sio.getap.app;

public class Classe {
	private Integer id;
	private String nom;

	public Classe() {

	}

	public Classe(Integer id, String nom) {
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

	@Override
	public String toString() {
		return "[" + nom + "]";
	}

}
