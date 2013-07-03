package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.Classe;

public interface IFClasseDAO {

	public List<Classe> getAllClasse();

	public Classe getClasseById(int id);

	public void addClasse(Classe classe);

	public void upDateClasse(Classe classe);

	public void deleteClasse(Classe classe);

	public int countClasses();

	public List<Classe> getAllClasseByProf(Long id);

}
