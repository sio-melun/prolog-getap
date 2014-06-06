package org.ldv.sio.getap.app;

/**
 * type d'accompagnement personnalisé. Ex : "SOS Matière"
 * 
 * Map la table AP
 */
public class AccPersonalise {
	private Integer id;
	private String nom;
	private int origineEtat;
	private Long idUser;

	// transiant
	private int count;
	private int idEleve;

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
}
