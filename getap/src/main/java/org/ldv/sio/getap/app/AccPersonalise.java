package org.ldv.sio.getap.app;

/**
 * type d'accompagnement personnalisé. Ex : "SOS Matière"
 * 
 * Map la table AP + ...
 */
public class AccPersonalise {
	private Integer id;
	private String nom;
	private int origineEtat;
	private Long idUser;

	// transiant
	private int count;
	private int idEleve;

	// transiant
	private String prenom;
	private int countap;
	private int dctapval;
	private int dctapref;
	private int dctapatt;

	public AccPersonalise() {
	}

	public AccPersonalise(Integer id, String nom, int origineEtat, Long idUser) {
		super();
		this.id = id;
		this.nom = nom;
		this.origineEtat = origineEtat;
		this.idUser = idUser;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getOrigineEtat() {
		return origineEtat;
	}

	public void setOrigineEtat(int origineEtat) {
		this.origineEtat = origineEtat;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public void setPrenom(String prenom) {
		// CHEVAL
		this.prenom = prenom;
	}

	public void setCountap(int countap) {
		// TODO Auto-generated method stub
		this.countap = countap;
	}

	public String getPrenom() {
		// TODO Auto-generated method stub
		return prenom;
	}

	public int getCountap() {
		// TODO Auto-generated method stub
		return countap;
	}

	public void setDctapval(int dctapval) {
		// TODO Auto-generated method stub
		this.dctapval = dctapval;
	}

	public int getDctapval() {
		// TODO Auto-generated method stub
		return dctapval;
	}

	public void setDctapatt(int dctapatt) {
		// TODO Auto-generated method stub
		this.dctapatt = dctapatt;
	}

	public int getDctapatt() {
		// TODO Auto-generated method stub
		return dctapatt;
	}

	public void setDctapref(int dctapref) {
		// TODO Auto-generated method stub
		this.dctapref = dctapref;
	}

	public int getDctapref() {
		// TODO Auto-generated method stub
		return dctapref;
	}

}
