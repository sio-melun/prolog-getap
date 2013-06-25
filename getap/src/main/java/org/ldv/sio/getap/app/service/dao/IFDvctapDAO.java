package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.User;

public interface IFDvctapDAO {

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByEleve(
			User eleve);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfInterv(
			User profi);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByProfPrinc(
			User profp);

	public List<DemandeValidationConsoTempsAccPers> getAllDVCTAPByClasse(
			String classe);

	public int getAllDVCTAPByEtat(int etat, Long id);

	public int getAllDVCTAPModifByEtat(Long id);

	public DemandeValidationConsoTempsAccPers getDVCTAPById(Long id);

	public void addDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public void updateDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public void deleteDVCTAP(DemandeValidationConsoTempsAccPers dctap);

	public boolean deleteDVCTAPById(Long id);

}
