package org.ldv.sio.getap.app;

import java.util.List;

public class DateStats {

	private String nomProf;
	private String prenomProf;
	private String dateAP;
	private int nbSolicitant;
	private String typeAP;
	private int firstMois;

	private String numMois;
	private String mois;
	static String[] tabLibMois = { "", "Janvier", "Fevrier", "Mars", "Avril",
			"Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre",
			"Novembre", "decembre" };

	private List<User> listEleve;
	private String prenomEleve;
	private String nomEleve;
	private String nomClasse;

	public DateStats() {

	}

	public DateStats(String mois, int nbSolicitant, String typeAP,
			int firstMois, String nomProf, String prenomProf, String dateAP,
			String numMois, String[] tabLibMois) {
		super();
		this.numMois = numMois;
		this.mois = tabLibMois[Integer.parseInt(numMois)];
		this.nbSolicitant = nbSolicitant;
		this.typeAP = typeAP;
		this.firstMois = firstMois;
		this.nomProf = nomProf;
		this.prenomProf = prenomProf;
		this.dateAP = dateAP;
	}

	public DateStats(String prenomEleve, String nomEleve, String nomClasse) {
		super();
		this.prenomEleve = prenomEleve;
		this.nomEleve = nomEleve;
		this.nomClasse = nomClasse;
	}

	public String getMois() {
		mois = tabLibMois[Integer.parseInt(numMois)];
		return mois;
	}

	public void setMois(String mois) {
		mois = tabLibMois[Integer.parseInt(numMois)];
		this.mois = mois;
	}

	public int getFirstMois() {
		return firstMois;
	}

	public void setFirstMois(int firstMois) {
		this.firstMois = firstMois;
	}

	public int getNbSolicitant() {
		return nbSolicitant;
	}

	public void setNbSolicitant(int nbSolicitant) {
		this.nbSolicitant = nbSolicitant;
	}

	public String getTypeAP() {
		return typeAP;
	}

	public void setTypeAP(String typeAP) {
		this.typeAP = typeAP;
	}

	public String getNumMois() {
		return numMois;
	}

	public void setNumMois(String numMois) {
		this.numMois = numMois;
	}

	public String getNomProf() {
		return nomProf;
	}

	public void setNomProf(String nomProf) {
		this.nomProf = nomProf;
	}

	public String getPrenomProf() {
		return prenomProf;
	}

	public void setPrenomProf(String prenomProf) {
		this.prenomProf = prenomProf;
	}

	public String getDateAP() {
		return dateAP;
	}

	public void setDateAP(String dateAP) {
		this.dateAP = dateAP;
	}

	public static String[] getTabLibMois() {
		return tabLibMois;
	}

	public static void setTabLibMois(String[] tabLibMois) {
		DateStats.tabLibMois = tabLibMois;
	}

	public String getPrenomEleve() {
		return prenomEleve;
	}

	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}

	public String getNomEleve() {
		return nomEleve;
	}

	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}

	public List<User> getListEleve() {
		return listEleve;
	}

	public void setListEleve(List<User> listEleve) {
		this.listEleve = listEleve;
	}

}
