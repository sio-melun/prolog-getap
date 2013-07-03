package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.Discipline;

public interface IFDisciplineDAO {

	public List<Discipline> getAllDiscipline();

	public Discipline getDisciplineById(int id);

	public void addDiscipline(Discipline dis);

	public void upDateDiscipline(Discipline dis);

	public void deleteDiscipline(Discipline dis);
}
