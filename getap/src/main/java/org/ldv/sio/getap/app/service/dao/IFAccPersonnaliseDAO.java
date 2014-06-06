package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.AccPersonalise;

public interface IFAccPersonnaliseDAO {

	public List<AccPersonalise> getAllAPForEleve();

	public List<AccPersonalise> getAllAPForProf();

	public List<AccPersonalise> getAllAPForAdmin();

	public AccPersonalise getAPById(int id);

	public AccPersonalise getAPByNom(String nom);

	public void addAP(AccPersonalise ap);

	public void upDateAP(AccPersonalise ap);

	public void deleteAP(AccPersonalise ap);

	public List<AccPersonalise> getApByType();

}
