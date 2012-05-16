package org.ldv.sio.getap.app;

public class Utilisateur {

	protected String nom;
	protected String prenom;
	protected String login;
	protected String pass;
	protected String classe;
	protected String mail;

	public Utilisateur(String prenom, String nom) {

		if (nom.contains("'")) {
			nom = nom.replace("'", "");
		}

		if (prenom.contains("'")) {
			prenom = prenom.replace("'", "");
		}

		this.nom = nom;
		this.prenom = prenom;

		this.login = prenom.charAt(0) + nom;
		this.pass = generate(5);
		this.classe = null;
		this.mail = null;
	}

	public String generate(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String pass = "";
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * 62); // Si tu supprimes des
															// lettres tu
															// diminues ce nb
			pass += chars.charAt(i);
		}
		System.out.println(pass);
		return pass;
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
