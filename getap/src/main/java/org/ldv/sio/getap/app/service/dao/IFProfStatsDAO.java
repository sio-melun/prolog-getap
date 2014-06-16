package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.AnneeScolaire;
import org.ldv.sio.getap.app.ProfStats;

public interface IFProfStatsDAO {

	public List<ProfStats> getAllAPForEachProf();

	public List<AnneeScolaire> getAllYearsForStatsProf();

	public List<ProfStats> getAllAPForEachProf(String annee);

	public List<Integer> getAllAPForStatsProf(String annee);

}
