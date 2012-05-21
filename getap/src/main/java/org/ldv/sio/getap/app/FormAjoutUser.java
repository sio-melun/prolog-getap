package org.ldv.sio.getap.app;


public class FormAjoutUser {

	private Long id;
	private String nom;
	private String prenom;
	private Integer classeId;
	private String classeNom;
	private String[] classe;
	private int disciplineId;
	private String disciplineNom;
	private int roleId;
	private String roleNom;

	public FormAjoutUser() {

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

	public Long getId() {
		return id;
	}

	public String[] getClasse() {
		return classe;
	}

	public void setClasse(String[] classe) {
		this.classe = classe;
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
