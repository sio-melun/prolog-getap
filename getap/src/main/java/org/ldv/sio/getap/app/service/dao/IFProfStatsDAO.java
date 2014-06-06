package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.ProfStats;

public interface IFProfStatsDAO {

	public List<Integer> getAllAPForStatsProf();

	public List<ProfStats> getAllAPForEachProf();

}
