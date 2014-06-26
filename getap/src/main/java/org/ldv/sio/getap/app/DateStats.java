package org.ldv.sio.getap.app;

public class DateStats {

	private String nomProf;
	private String prenomProf;
	private String dateAP;
	private int nbParticipant;
	private String typeAP;
	private int firstMois;

	private String numMois;
	private String mois;
	static String[] tabLibMois = { "", "Janvier", "Fevrier", "Mars", "Avril",
			"Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre",
			"Novembre", "decembre" };

	public DateStats() {

	}

	public DateStats(String mois, int nbParticipant, String typeAP,
			int firstMois, String nomProf, String prenomProf, String dateAP,
			String numMois, String[] tabLibMois) {
		super();
		this.numMois = numMois;
		this.mois = tabLibMois[Integer.parseInt(numMois)];
		this.nbParticipant = nbParticipant;
		this.typeAP = typeAP;
		this.firstMois = firstMois;
		this.nomProf = nomProf;
		this.prenomProf = prenomProf;
		this.dateAP = dateAP;
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

	public int getNbParticipant() {
		return nbParticipant;
	}

	public void setNbParticipant(int nbParticipant) {
		this.nbParticipant = nbParticipant;
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

}
