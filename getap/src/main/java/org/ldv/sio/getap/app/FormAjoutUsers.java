package org.ldv.sio.getap.app;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FormAjoutUsers {

	private String nom;
	private CommonsMultipartFile file;

	public FormAjoutUsers() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
		this.nom = file.getOriginalFilename();
	}

}
