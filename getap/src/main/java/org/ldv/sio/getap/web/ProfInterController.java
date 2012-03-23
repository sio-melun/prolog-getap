package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			DemandeConsoTempsAccPers dctap, Model model) {

		System.out.println("TEST id recu :" + dctap.getId());

		DemandeConsoTempsAccPers currentDctap = manager.getDCTAPById(Long
				.valueOf(id));

		// valorise le bean de vue avec le dctap courant
		dctap.setId(currentDctap.getId()); // en provenance d'un champ caché
		dctap.setDateAction(currentDctap.getDateAction());
		dctap.setMinutes(currentDctap.getMinutes());
		dctap.setAccPers(currentDctap.getAccPers());

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

}
