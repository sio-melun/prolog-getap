package org.ldv.sio.getap.app;

/**
 * Permet d'obtenir les statistiques des professeurs. SELECT ap.libelle AS
 * libelle, count(dctap.id) AS countap FROM dctap, ap WHERE ap.id = dctap.idAP
 * GROUP BY ap.libelle
 */

public class TypeStats {
	private String libelle;
	private int countap;

	public TypeStats() {
	}

	public TypeStats(String libelle, int countap) {
		super();
		this.libelle = libelle;
		this.countap = countap;
	}

	public void setCountap(int countap) {
		// TODO Auto-generated method stub
		this.countap = countap;
	}

	public int getCountap() {
		// TODO Auto-generated method stub
		return countap;
	}

	public void setLibelle(String libelle) {
		// TODO Auto-generated method stub
		this.libelle = libelle;
	}

	public String getLibelle() {
		// TODO Auto-generated method stub
		return libelle;
	}
}
