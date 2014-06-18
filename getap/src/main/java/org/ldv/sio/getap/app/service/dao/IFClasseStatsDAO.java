package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.AnneeScolaire;
import org.ldv.sio.getap.app.ClasseStats;

public interface IFClasseStatsDAO {

	public List<ClasseStats> getAllAPByClasse(String idClasse, String annee);

	public List<ClasseStats> getAllProfesseursByClasse(String idClasse,
			String annee);

	public List<Integer> getAlldctapByClasse(String idClasse, String annee);

	public List<ClasseStats> getAllClassesForStats();

	public List<AnneeScolaire> getAllYearsForClasseStats();

}
