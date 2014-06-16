package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.ClasseStats;

public interface IFParClasseStatsDAO {

	public List<ClasseStats> getAllAPByClasse(String idClasse);

	public List<String> getFirstClasse();

	public List<ClasseStats> getAllProfesseursByClasse(String idClasse);

	public List<Integer> getAlldctapByClasse(String idClasse);

	public List<ClasseStats> getAllClassesForStats();

}
