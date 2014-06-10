package org.ldv.sio.getap.app;

/**
 * Permet d'obtenir les statistiques des professeurs. SELECT ap.libelle AS
 * libelle, count(dctap.id) AS countap FROM dctap, ap WHERE ap.id = dctap.idAP
 * GROUP BY ap.libelle
 */

public class LoginInfo {
	private String lastlog;
	private String countlog;

	public LoginInfo() {
	}

	public LoginInfo(String libelle, String countap) {
		super();
		this.lastlog = libelle;
		this.countlog = countap;
	}

	public void setLastlog(String lastlog) {
		// TODO Auto-generated method stub
		this.lastlog = lastlog;
	}

	public void setCountlog(String countlog) {
		// TODO Auto-generated method stub
		this.countlog = countlog;
	}

	public String getLastlog() {
		return lastlog;
	}

	public String getCountlog() {
		return countlog;
	}

}
