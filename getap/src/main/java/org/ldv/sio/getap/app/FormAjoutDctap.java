package org.ldv.sio.getap.app;

public class FormAjoutDctap {

	private Long id;
	private String anneeScolaire;
	private java.sql.Date date;
	private Integer minutes;
	private Long profId;
	private String profNom;
	private int accPersId;
	private String accPersNom;
	private Long eleveId;
	private String eleveNom;
	private int etat;

	public FormAjoutDctap() {
		super();
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(String anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Long getProfId() {
		return profId;
	}

	public void setProfId(Long profId) {
		this.profId = profId;
	}

	public String getProfNom() {
		return profNom;
	}

	public void setProfNom(String profNom) {
		this.profNom = profNom;
	}

	public int getAccPersId() {
		return accPersId;
	}

	public void setAccPersId(int accPersId) {
		this.accPersId = accPersId;
	}

	public String getAccPersNom() {
		return accPersNom;
	}

	public void setAccPersNom(String accPersNom) {
		this.accPersNom = accPersNom;
	}

	public Long getEleveId() {
		return eleveId;
	}

	public void setEleveId(Long eleveId) {
		this.eleveId = eleveId;
	}

	public String getEleveNom() {
		return eleveNom;
	}

	public void setEleveNom(String eleveNom) {
		this.eleveNom = eleveNom;
	}

}
