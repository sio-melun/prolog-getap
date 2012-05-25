package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.FormListConsoForProfInter;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/prof-intervenant/*")
public class ProfInterController {

	@Autowired
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editDCTAPById(@RequestParam("id") String id,
			FormListConsoForProfInter dctap, Model model) {

		System.out.println("TEST id recu :" + dctap.getId());

		model.addAttribute("lesAP", manager.getAllAP());

		DemandeConsoTempsAccPers currentDctap = manager.getDCTAPById(Long
				.valueOf(id));
		if (currentDctap.getEtat() == 0 || currentDctap.getEtat() == 3
				|| currentDctap.getEtat() > 40) {
			// valorise le bean de vue avec le dctap courant
			dctap.setId(currentDctap.getId()); // en provenance d'un champ caché
			dctap.setDateAction(currentDctap.getDateAction());
			dctap.setMinutes(currentDctap.getMinutes());
			dctap.setAccPersId(currentDctap.getAccPers().getId());

			return "prof-intervenant/edit";
		}
		return "prof-intervenant/index";
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(Model model) {
		User me = UtilSession.getUserInSession();
		model.addAttribute("listdctaps", manager.getAllDCTAPByProfInterv(me));
		Long id = me.getId();
		model.addAttribute("etat0", manager.getAllDCTAPByEtat(0, id));
		model.addAttribute("etat1", manager.getAllDCTAPByEtat(1, id));
		model.addAttribute("etat2", manager.getAllDCTAPByEtat(2, id));
		model.addAttribute("etat3", manager.getAllDCTAPByEtat(3, id));

		model.addAttribute("etat41", manager.getAllDCTAPByEtat(41, id));
		model.addAttribute("etat42", manager.getAllDCTAPByEtat(42, id));
		model.addAttribute("etat43", manager.getAllDCTAPByEtat(43, id));
		model.addAttribute("etat44", manager.getAllDCTAPByEtat(44, id));
		model.addAttribute("etat45", manager.getAllDCTAPByEtat(45, id));
		model.addAttribute("etat46", manager.getAllDCTAPByEtat(46, id));
		model.addAttribute("etat47", manager.getAllDCTAPByEtat(47, id));

		model.addAttribute("etat5", manager.getAllDCTAPByEtat(5, id));
		model.addAttribute("etat6", manager.getAllDCTAPByEtat(6, id));
		model.addAttribute("etat7", manager.getAllDCTAPByEtat(7, id));
	}

	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public String doeditDCTAPById(FormListConsoForProfInter formDctap,
			BindingResult bindResult, Model model) {
		System.out.println("TEST :" + formDctap.getId());
		System.out.println("TEST :" + model);

		if (bindResult.hasErrors())
			return "prof-intervenant/edit";
		else {

			DemandeConsoTempsAccPers dctapForUpdate = manager.getDCTAPById(Long
					.valueOf(formDctap.getId()));

			// 41=date; 42=durée; 43=type; 44=date+durée; 45=durée+type;
			// 46=date+type; 47=type+date+durée

			AccPersonalise acc = manager.getAPById(formDctap.getAccPersId());
			String accPersNom = acc.getNom();
			if (!dctapForUpdate.getDateAction().equals(
					formDctap.getDateAction())
					&& !dctapForUpdate.getMinutes().equals(
							formDctap.getMinutes())
					&& !dctapForUpdate.getAccPers().getNom().equals(accPersNom)) {
				dctapForUpdate.setEtat(47);
			} else if (!dctapForUpdate.getDateAction().equals(
					formDctap.getDateAction())
					&& !dctapForUpdate.getAccPers().getNom().equals(accPersNom)) {
				dctapForUpdate.setEtat(46);
			} else if (!dctapForUpdate.getMinutes().equals(
					formDctap.getMinutes())
					&& !dctapForUpdate.getAccPers().getNom().equals(accPersNom)) {
				dctapForUpdate.setEtat(45);
			} else if (!dctapForUpdate.getDateAction().equals(
					formDctap.getDateAction())
					&& !dctapForUpdate.getMinutes().equals(
							formDctap.getMinutes())) {
				dctapForUpdate.setEtat(44);
			} else if (!dctapForUpdate.getAccPers().getNom().equals(accPersNom)) {
				dctapForUpdate.setEtat(43);
			} else if (!dctapForUpdate.getMinutes().equals(
					formDctap.getMinutes())) {
				dctapForUpdate.setEtat(42);
			} else if (!dctapForUpdate.getDateAction().equals(
					formDctap.getDateAction())) {
				dctapForUpdate.setEtat(41);
			}

			dctapForUpdate.setDateAction(formDctap.getDateAction());
			dctapForUpdate.setMinutes(formDctap.getMinutes());
			dctapForUpdate.setAccPers(manager.getAPById(formDctap
					.getAccPersId()));

			manager.updateDCTAP(dctapForUpdate);

			return "redirect:/app/prof-intervenant/index";
		}
	}

	@RequestMapping(value = "refuse/{id}", method = RequestMethod.GET)
	public String refuseDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers dctap = manager.getDCTAPById(Long.valueOf(id));

		// Test que la DCTAP appartient à la bonne personne
		if (dctap.getProf().equals(UtilSession.getUserInSession())
				&& (dctap.getEtat() == 0 || dctap.getEtat() == 3 || dctap
						.getEtat() > 40)) {
			dctap.setEtat(6);
			manager.updateDCTAP(dctap);
		}

		return "redirect:/app/prof-intervenant/index";
	}

	@RequestMapping(value = "valid/{id}", method = RequestMethod.GET)
	public String validDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers dctap = manager.getDCTAPById(Long.valueOf(id));

		// Test que la DCTAP appartient à la bonne personne
		if (dctap.getProf().equals(UtilSession.getUserInSession())
				&& (dctap.getEtat() == 0 || dctap.getEtat() == 3)) {
			dctap.setEtat(5);
			manager.updateDCTAP(dctap);
		}

		return "redirect:/app/prof-intervenant/index";
	}
}
