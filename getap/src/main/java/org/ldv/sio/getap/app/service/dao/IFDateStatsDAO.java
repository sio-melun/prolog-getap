package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.DateStats;

public interface IFDateStatsDAO {

	public List<DateStats> getAllDemandeByMois(String mois, String annee);

	public List<DateStats> getAllMois(String annee);

	public int getFirstMois(String annee);

}
