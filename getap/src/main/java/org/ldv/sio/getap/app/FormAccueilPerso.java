package org.ldv.sio.getap.app;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FormAccueilPerso {
	private CommonsMultipartFile img;
	private CommonsMultipartFile logo;
	private String imgString;
	private String logoString;
	private String titre;
	private String texte;

	public FormAccueilPerso() {

	}

	public CommonsMultipartFile getImg() {
		return img;
	}

	public void setImg(CommonsMultipartFile img) {
		this.img = img;
		this.imgString = img.getOriginalFilename();
	}

	public CommonsMultipartFile getLogo() {
		return logo;
	}

	public void setLogo(CommonsMultipartFile logo) {
		this.logo = logo;
		this.logoString = logo.getOriginalFilename();
	}

	public String getImgString() {
		return imgString;
	}

	public void setImgString(String imgString) {
		this.imgString = imgString;
	}

	public String getLogoString() {
		return logoString;
	}

	public void setLogoString(String logoString) {
		this.logoString = logoString;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

}