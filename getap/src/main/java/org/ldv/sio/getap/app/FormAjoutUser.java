package org.ldv.sio.getap.app;

public class FormAjoutUser {

	private Long id;
	private String nom;
	private String prenom;
	private Integer classeId;
	private String classeNom;
	private String role;

	public FormAjoutUser() {

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getClasseId() {
		return classeId;
	}

	public void setClasseId(Integer classeId) {
		this.classeId = classeId;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public void setClasseNom(String classeNom) {
		this.classeNom = classeNom;
	}

	public String classe() {
		String str = "ID : " + this.getClasseId();
		str += "\nNom : " + this.getClasseNom();
		return str;
	}
}
