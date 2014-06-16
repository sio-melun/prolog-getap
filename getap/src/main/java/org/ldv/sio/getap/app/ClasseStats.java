package org.ldv.sio.getap.app;

/**
 * Permet d'obtenir les statistiques par classes
 */

public class ClasseStats {

	// variable pour le tableau des classes
	private String nomEleve;
	private String prenomEleve;
	private int countApEleve;
	private int dctapvalideEleve;
	private int dctapattenteEleve;
	private int dctaprefuseEleve;
	private int idEleveSQL;
	private int countlogEleve;
	private String lastlogEleve;
	private int firstClasse;

	// variable pour le tableau des profs
	private String nomProf;
	private String prenomProf;
	private String lastlogProf;
	private int countapProf;
	private int dctapvalideProf;
	private int dctapattenteProf;
	private int dctaprefuseProf;
	private int idProfSQL;
	private int countlogProf;

	// variable pour le tableau des stats generales des élèves par classes
	private int countApTotal;
	private int dctapvalideTotal;
	private int dctapattenteTotal;
	private int dctaprefuseTotal;

	private int idClasse;
	private String libelleClasse;

	public ClasseStats() {

	}

	public ClasseStats(int idClasse, String libelleClasse) {
		super();
		this.idClasse = idClasse;
		this.libelleClasse = libelleClasse;
	}

	// Constructeur pour eleve
	public ClasseStats(String nomEleve, String prenomEleve, int countApEleve,
			int dctapvalideEleve, int dctapattenteEleve, int dctaprefuseEleve,
			int idEleveSQL, int countlogEleve, String lastlogEleve,
			int firstClasse) {

		super();
		this.nomEleve = nomEleve;
		this.prenomEleve = prenomEleve;
		this.countApEleve = countApEleve;
		this.dctapvalideEleve = dctapvalideEleve;
		this.dctapattenteEleve = dctapattenteEleve;
		this.dctaprefuseEleve = dctaprefuseEleve;
		this.lastlogEleve = lastlogEleve;
		this.countlogEleve = countlogEleve;
		this.firstClasse = firstClasse;

	}

	// Constructeur pour profs
	public ClasseStats(String nomProf, String prenomProf, int dctapvalideProf,
			int dctapattenteProf, int dctaprefuseProf, int countapProf,
			String lastlogProf, int countlogProf) {

		super();
		this.nomProf = nomProf;
		this.prenomProf = prenomProf;
		this.countapProf = countapProf;
		this.dctapvalideProf = dctapvalideProf;
		this.dctapattenteProf = dctapattenteProf;
		this.dctaprefuseProf = dctaprefuseProf;
		this.lastlogProf = lastlogProf;
		this.countlogProf = countlogProf;

	}

	// Constructeur des stats generales pour une classe
	public ClasseStats(int countApTotal, int dctapvalideTotal,
			int dctapattenteTotal, int dctaprefuseTotal) {
		super();
		this.countApTotal = countApTotal;
		this.dctapvalideTotal = dctapvalideTotal;
		this.dctapattenteTotal = dctapattenteTotal;
		this.dctaprefuseTotal = dctaprefuseTotal;
	}

	public String getNomEleve() {
		return nomEleve;
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

	public int getCountapProf() {
		return countapProf;
	}

	public void setCountapProf(int countapProf) {
		this.countapProf = countapProf;
	}

	public int getDctapvalideProf() {
		return dctapvalideProf;
	}

	public void setDctapvalideProf(int dctapvalideProf) {
		this.dctapvalideProf = dctapvalideProf;
	}

	public int getDctapattenteProf() {
		return dctapattenteProf;
	}

	public void setDctapattenteProf(int dctapattenteProf) {
		this.dctapattenteProf = dctapattenteProf;
	}

	public int getDctaprefuseProf() {
		return dctaprefuseProf;
	}

	public void setDctaprefuseProf(int dctaprefuseProf) {
		this.dctaprefuseProf = dctaprefuseProf;
	}

	public int getIdProfSQL() {
		return idProfSQL;
	}

	public void setIdProfSQL(int idProfSQL) {
		this.idProfSQL = idProfSQL;
	}

	public int getCountlogProf() {
		return countlogProf;
	}

	public void setCountlogProf(int countlogProf) {
		this.countlogProf = countlogProf;
	}

	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}

	public int getFirstClasse() {
		return firstClasse;
	}

	public void setFirstClasse(int firstClasse) {
		this.firstClasse = firstClasse;
	}

	public String getPrenomEleve() {
		return prenomEleve;
	}

	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}

	public int getCountapEleve() {
		return countApEleve;
	}

	public void setCountApEleve(int countApEleve) {
		this.countApEleve = countApEleve;
	}

	public int getDctapvalideEleve() {
		return dctapvalideEleve;
	}

	public void setDctapvalideEleve(int dctapvalideEleve) {
		this.dctapvalideEleve = dctapvalideEleve;
	}

	public int getDctapattenteEleve() {
		return dctapattenteEleve;
	}

	public void setDctapattenteEleve(int dctapattenteEleve) {
		this.dctapattenteEleve = dctapattenteEleve;
	}

	public int getDctaprefuseEleve() {
		return dctaprefuseEleve;
	}

	public void setDctaprefuseEleve(int dctaprefuseEleve) {
		this.dctaprefuseEleve = dctaprefuseEleve;
	}

	public int getIdEleveSQL() {
		return idEleveSQL;
	}

	public void setIdEleveSQL(int idEleveSQL) {
		this.idEleveSQL = idEleveSQL;
	}

	public int getCountlogEleve() {
		return countlogEleve;
	}

	public void setCountlogEleve(int countlogEleve) {
		this.countlogEleve = countlogEleve;
	}

	public String getLastlogEleve() {
		return lastlogEleve;
	}

	public void setLastlogEleve(String lastlogEleve) {
		this.lastlogEleve = lastlogEleve;
	}

	public String getLastlogProf() {
		return lastlogProf;
	}

	public void setLastlogProf(String lastlogProf) {
		this.lastlogProf = lastlogProf;
	}

	public int getCountApTotal() {
		return countApTotal;
	}

	public void setCountApTotal(int countApTotal) {
		this.countApTotal = countApTotal;
	}

	public int getDctapvalideTotal() {
		return dctapvalideTotal;
	}

	public void setDctapvalideTotal(int dctapvalideTotal) {
		this.dctapvalideTotal = dctapvalideTotal;
	}

	public int getDctapattenteTotal() {
		return dctapattenteTotal;
	}

	public void setDctapattenteTotal(int dctapattenteTotal) {
		this.dctapattenteTotal = dctapattenteTotal;
	}

	public int getDctaprefuseTotal() {
		return dctaprefuseTotal;
	}

	public void setDctaprefuseTotal(int dctaprefuseTotal) {
		this.dctaprefuseTotal = dctaprefuseTotal;
	}

	public int getCountApEleve() {
		return countApEleve;
	}

	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public String getLibelleClasse() {
		return libelleClasse;
	}

	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}

}
