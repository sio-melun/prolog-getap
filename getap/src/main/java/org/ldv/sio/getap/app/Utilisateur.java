package org.ldv.sio.getap.app;

public class Utilisateur {

	protected String nom;
	protected String prenom;
	protected String login;
	protected String pass;
	protected String classe;
	protected String mail;

	public Utilisateur(String prenom, String nom) {
		this.nom = nom;
		this.prenom = prenom;
		this.login = prenom.charAt(0) + nom;
		this.pass = login;
		this.classe = null;
		this.mail = null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
