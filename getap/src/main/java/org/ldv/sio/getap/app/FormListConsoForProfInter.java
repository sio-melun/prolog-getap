package org.ldv.sio.getap.app;

public class FormListConsoForProfInter {

	private long id;
	private java.sql.Date dateAction;
	private int minutes;
	private long accPersId;
	private String accPersNom;
	private String accPers;

	public FormListConsoForProfInter() {

	}

	public String getAccPers() {
		return accPers;
	}

	public void setAccPers(String accPers) {
		this.accPers = accPers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getAccPersId() {
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

}
