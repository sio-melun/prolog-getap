package org.ldv.sio.getap.web;

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

	@RequestMapping(value = "listdctap", method = RequestMethod.GET)
	public String mesdctap(Model model) {
		User me = UtilSession.getUserInSession();
		model.addAttribute("listdctaps", manager.getAllDCTAPByProfInterv(me));
		return "prof-intervenant/listdctap";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editDCTAPById(@RequestParam("id") String id,
			FormListConsoForProfInter dctap, Model model) {

		System.out.println("TEST id recu :" + dctap.getId());

		model.addAttribute("lesAP", manager.getAllAP());

		DemandeConsoTempsAccPers currentDctap = manager.getDCTAPById(Long
				.valueOf(id));

		// valorise le bean de vue avec le dctap courant
		dctap.setId(currentDctap.getId()); // en provenance d'un champ caché
		dctap.setDateAction(currentDctap.getDateAction());
		dctap.setMinutes(currentDctap.getMinutes());
		dctap.setAccPersId(currentDctap.getAccPers().getId());

		return "prof-intervenant/edit";
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index() {

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

			// valorise l'objet de la base à partir du bean de vue
			dctapForUpdate.setDateAction(formDctap.getDateAction());
			dctapForUpdate.setMinutes(formDctap.getMinutes());
			dctapForUpdate.setAccPers(manager.getAPById(formDctap
					.getAccPersId()));
			dctapForUpdate.setEtat(2);

			manager.updateDCTAP(dctapForUpdate);

			return "redirect:/app/prof-intervenant/listdctap";
		}
	}

	@RequestMapping(value = "refuse/{id}", method = RequestMethod.GET)
	public String deleteDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers dctap = manager.getDCTAPById(Long.valueOf(id));

		// Test que la DCTAP appartient à la bonne personne
		if (dctap.getProf().equals(UtilSession.getUserInSession())) {
			dctap.setEtat(3);
			manager.updateDCTAP(dctap);
			return "redirect:/app/prof-intervenant/index";
		}

		return "redirect:/app/prof-intervenant/listdctap";
	}
}
