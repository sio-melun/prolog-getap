package org.ldv.sio.getap.app;

public class FormAjoutUser {

	private Long id;
	private String nom;
	private String prenom;
	private Integer classeId;
	private String classeNom;
	private int roleId;
	private String roleNom;

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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleNom() {
		return roleNom;
	}

	public void setRoleNom(String roleNom) {
		this.roleNom = roleNom;
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
