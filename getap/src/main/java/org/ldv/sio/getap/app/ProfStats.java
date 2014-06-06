package org.ldv.sio.getap.app;

/**
 * Permet d'obtenir les statistiques des professeurs.
 */

public class ProfStats {
	private String nom;
	private String prenom;
	private int countap;
	private int dctapvalide;
	private int dctapattente;
	private int dctaprefuse;

	public ProfStats() {
	}

	public ProfStats(String nom, String prenom, int dctapvalide, int countap,
			int dctapattente, int dctaprefuse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.countap = countap;
		this.dctapvalide = dctapvalide;
		this.dctapattente = dctapattente;
		this.dctaprefuse = dctaprefuse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		// CHEVAL
		this.prenom = prenom;
	}

	public void setCountap(int countap) {
		// TODO Auto-generated method stub
		this.countap = countap;
	}

	public String getPrenom() {
		// TODO Auto-generated method stub
		return prenom;
	}

	public int getCountap() {
		// TODO Auto-generated method stub
		return countap;
	}

	public void setDctapvalide(int dctapvalide) {
		// TODO Auto-generated method stub
		this.dctapvalide = dctapvalide;
	}

	public int getDctapvalide() {
		// TODO Auto-generated method stub
		return dctapvalide;
	}

	public void setDctapattente(int dctapattente) {
		// TODO Auto-generated method stub
		this.dctapattente = dctapattente;
	}

	public int getDctapattente() {
		// TODO Auto-generated method stub
		return dctapattente;
	}

	public void setDctaprefuse(int dctaprefuse) {
		// TODO Auto-generated method stub
		this.dctaprefuse = dctaprefuse;
	}

	public int getDctaprefuse() {
		// TODO Auto-generated method stub
		return dctaprefuse;
	}
}
