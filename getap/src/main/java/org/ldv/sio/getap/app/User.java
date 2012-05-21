package org.ldv.sio.getap.app;

/**
 * A User with role
 */
public class User {

	private Long id;
	private String nom;
	private String prenom;
	private Classe classe;
	private String role;
	private String login;
	private String pass;
	private String mail;
	private String[] lesClasses;

	public User() {
	}

	public User(Long id, String firstName, String lastName, Classe classe,
			String role, String login, String pass, String mail) {
		this.id = id;
		this.prenom = firstName;
		this.nom = lastName;
		this.classe = classe;
		this.role = role;
		this.login = login;
		this.pass = pass;
		this.mail = mail;
	}

	public User(Long id, String firstName, String lastName, Classe classe,
			String role) {
		this.id = id;
		this.prenom = firstName;
		this.nom = lastName;
		this.classe = classe;
		this.role = role;
		this.login = getLogin();
		this.pass = getPass();
		this.mail = getMail();
	}

	public User(Long id, String firstName, String lastName, Classe classe,
			String role, String[] lesClasses) {
		this.id = id;
		this.prenom = firstName;
		this.nom = lastName;
		this.classe = classe;
		this.role = role;
		this.login = getLogin();
		this.pass = getPass();
		this.mail = getMail();
		this.lesClasses = lesClasses;
	}

	@Override
	public String toString() {
		return "User(" + prenom + ":" + nom + "," + role + "," + classe + ","
				+ login + "," + mail + ")";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String[] getLesClasses() {
		return lesClasses;
	}

	public void setLesClasses(String[] lesClasses) {
		this.lesClasses = lesClasses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

}
