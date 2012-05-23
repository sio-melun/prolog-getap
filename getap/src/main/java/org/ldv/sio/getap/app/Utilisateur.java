package org.ldv.sio.getap.app;

public class Utilisateur {

	protected String nom;
	protected String prenom;
	protected String ine;
	protected String login;
	protected String pass;
	protected String classe;

	public Utilisateur(String nom, String prenom, String ine, String classe) {

		if (nom.contains("'")) {
			nom = nom.replace("'", "");
		}

		if (prenom.contains("'")) {
			prenom = prenom.replace("'", "");
		}

		this.nom = nom;
		this.prenom = prenom;
		this.ine = ine;
		this.login = login();
		this.pass = generate(5);
		this.classe = classe;
	}

	public String generate(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		String pass = "";
		for (int x = 0; x < length; x++) {
			int i = (int) Math.floor(Math.random() * 36); // Si tu supprimes des
															// lettres tu
															// diminues ce nb
			pass += chars.charAt(i);
		}
		return pass;
	}

	public String login() {
		String login;
		if ((this.getPrenom().charAt(0) + this.getNom()).length() >= 6) {
			login = (this.getPrenom().charAt(0) + this.getNom()).toLowerCase();
		} else if ((this.getPrenom().charAt(0) + this.getNom()).length() == 5) {
			login = (this.getPrenom().charAt(0) + "_" + this.getNom())
					.toLowerCase();
		} else if ((this.getPrenom() + this.getNom()).length() < 6) {
			login = (this.getPrenom() + "_" + this.getNom()).toLowerCase();

		} else {
			login = (this.getPrenom() + this.getNom()).toLowerCase();
		}
		if (login.length() > 10) {
			login = login.substring(0, 10);
		}
		if (login.contains('é' + "") || login.contains('è' + "")) {
			login = login.replace('é', 'e');
			login = login.replace('è', 'e');
		}
		if (login.contains('à' + "") || login.contains('â' + "")) {
			login = login.replace('à', 'a');
			login = login.replace('â', 'a');
		}
		if (login.contains("'" + "")) {
			login = login.replace("'", "");
		}

		return login;
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
