package org.ldv.sio.getap.app;

import java.sql.Date;

/**
 * Demande de validation d'un temps d'accompagnement personnalisé
 * 
 * La classe ne devrait-elle pas s'appeler DdeValidTempsAccPers ou quelque chose
 * comme ça ? L'élève demande à faire valider un temps d'accompagnement passé,
 * pas à consommer un temps futur.
 * 
 */

public class DemandeConsoTempsAccPers {
	/**
	 * Identifiant de la DCTAP
	 */
	private Long id;
	/**
	 * Année scolaire de la demande, par exemple "2011-2012"
	 */
	private String anneeScolaire;
	/**
	 * Date de réalisation de l'accompagnement
	 * 
	 */
	private java.sql.Date dateAction;
	/**
	 * Nombre de minutes d'accompagnement personnalisé à valider
	 */
	private Integer minutes;
	/**
	 * Professeur ayant assuré l'accompagnement personnalisé
	 */
	private User prof;
	/**
	 * Nature de l'accompagnement personnalisé associé à la demande
	 */
	private AccPersonalise accPers;
	/**
	 * Identifiant de l'élève ayant réalisé l'accompagnement personnalisé
	 */
	private User eleve;
	/**
	 * Etat actuel de la demande de validation. Valeurs admissibles :
	 * <ul>
	 * <li>0 - demande créée par l'élève</li>
	 * <li>1 - demande modifiée par le professeur</li>
	 * <li>2 - demande validée par le professeur</li>
	 * <li>3 - demande refusée par le professeur</li>
	 * <li>4 - demande détruite par l'élève</li>
	 * </ul>
	 */
	private int etat;

	/**
	 * constructeur par défaut
	 */
	public DemandeConsoTempsAccPers() {

	}

	/**
	 * Constructeur permettant de créer une demande complète.
	 * 
	 * @param id
	 *            identifiant de la demande, est-ce une bonne idée ? En général
	 *            c'est une valeur fournie par la couche de persistance
	 * @param anneeScolaire
	 * @param date
	 * @param minutes
	 * @param prof
	 * @param accPers
	 * @param idEleve
	 * @param etat
	 */
	public DemandeConsoTempsAccPers(Long id, String anneeScolaire, Date date,
			Integer minutes, User prof, AccPersonalise accPers, User eleve,
			int etat) {
		super();
		this.id = id;
		this.anneeScolaire = anneeScolaire;
		this.dateAction = date;
		this.minutes = minutes;
		this.prof = prof;
		this.accPers = accPers;
		this.eleve = eleve;
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

	public java.sql.Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(java.sql.Date date) {
		this.dateAction = date;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public User getProf() {
		return prof;
	}

	public void setProf(User prof) {
		this.prof = prof;
	}

	public AccPersonalise getAccPers() {
		return accPers;
	}

	public void setAccPers(AccPersonalise accPers) {
		this.accPers = accPers;
	}

	public User getEleve() {
		return eleve;
	}

	public void setEleve(User eleve) {
		this.eleve = eleve;
	}

	public int getEtat() {
		return etat;
	}

	/**
	 * Permet de modifier l'état de la demande
	 * 
	 * @param etat
	 *            prend ses valeur dans :
	 *            <ul>
	 *            <li>0 - demande créée par l'élève</li>
	 *            <li>1 - demande modifiée par le professeur</li>
	 *            <li>2 - demande validée par le professeur</li>
	 *            <li>3 - demande refusée par le professeur</li>
	 *            <li>4 - demande détruite par l'élève</li>
	 *            </ul>
	 */
	public void setEtat(int etat) {
		// TODO vérifier que la transition vers le nouvel état est bien
		// autorisée
		this.etat = etat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anneeScolaire == null) ? 0 : anneeScolaire.hashCode());
		result = prime * result
				+ ((dateAction == null) ? 0 : dateAction.hashCode());
		result = prime * result + ((eleve == null) ? 0 : eleve.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandeConsoTempsAccPers other = (DemandeConsoTempsAccPers) obj;
		if (anneeScolaire == null) {
			if (other.anneeScolaire != null)
				return false;
		} else if (!anneeScolaire.equals(other.anneeScolaire))
			return false;
		if (dateAction == null) {
			if (other.dateAction != null)
				return false;
		} else if (!dateAction.equals(other.dateAction))
			return false;
		if (eleve == null) {
			if (eleve != null)
				return false;
		} else if (!eleve.equals(other.eleve))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DemandeConsoTempsAccPers [id=" + id + ", anneeScolaire="
				+ anneeScolaire + ", dateAction=" + dateAction + ", minutes="
				+ minutes + ", prof=" + prof + ", accPers=" + accPers
				+ ", eleve=" + eleve + ", etat=" + etat + "]";
	}

}
