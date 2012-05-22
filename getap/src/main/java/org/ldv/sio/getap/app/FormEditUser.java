package org.ldv.sio.getap.app;

public class FormEditUser {

	private Long id;
	private String nom;
	private String prenom;
	private int classeId;
	private String classeNom;
	private String role;
	private String login;
	private String pass;
	private String mail;
	private String[] classe;
	private int disciplineId;
	private String disciplineNom;

	public String[] getClasse() {
		return classe;
	}

	public void setClasse(String[] classe) {
		this.classe = classe;
	}

	public int getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(int disciplineId) {
		this.disciplineId = disciplineId;
	}

	public String getDisciplineNom() {
		return disciplineNom;
	}

	public void setDisciplineNom(String disciplineNom) {
		this.disciplineNom = disciplineNom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public FormEditUser() {
		super();
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

	public int getClasseId() {
		return classeId;
	}

	public void setClasseId(int classeId) {
		this.classeId = classeId;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public void setClasseNom(String classeNom) {
		this.classeNom = classeNom;
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

}
