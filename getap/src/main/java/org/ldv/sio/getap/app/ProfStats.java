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
	private int idProf;

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
		this.prenom = prenom;
	}

	public void setCountap(int countap) {
		this.countap = countap;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getCountap() {
		return countap;
	}

	public void setDctapvalide(int dctapvalide) {
		this.dctapvalide = dctapvalide;
	}

	public int getDctapvalide() {
		return dctapvalide;
	}

	public void setDctapattente(int dctapattente) {
		this.dctapattente = dctapattente;
	}

	public int getDctapattente() {
		return dctapattente;
	}

	public void setDctaprefuse(int dctaprefuse) {
		this.dctaprefuse = dctaprefuse;
	}

	public int getDctaprefuse() {
		return dctaprefuse;
	}

	public void setId(int idProf) {
		this.idProf = idProf;
	}

	public int getId() {
		return idProf;
	}
}
