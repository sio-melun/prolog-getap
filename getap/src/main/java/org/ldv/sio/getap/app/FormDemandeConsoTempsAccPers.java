package org.ldv.sio.getap.app;

/**
 * Form backing bean for DemandeConsoTempsAccPers
 */

public class FormDemandeConsoTempsAccPers {

	private long id;
	private int anneeScolaire;
	private java.sql.Date dateAction;
	private int minutes;
	private long profId;
	private String profNom;
	private int accPersId;
	private long idEleve;
	private int etat;

	public FormDemandeConsoTempsAccPers() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(int anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public java.sql.Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(java.sql.Date dateAction) {
		this.dateAction = dateAction;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public long getProfId() {
		return profId;
	}

	public void setProfId(long profId) {
		this.profId = profId;
	}

	public int getAccPersId() {
		return accPersId;
	}

	public void setAccPersId(int accPersId) {
		this.accPersId = accPersId;
	}

	public long getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(long idEleve) {
		this.idEleve = idEleve;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getProfNom() {
		return profNom;
	}

	public void setProfNom(String profNom) {
		this.profNom = profNom;
	}

}
