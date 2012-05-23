package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/prof-principal/*")
public class ProfPrinController {

	@Autowired
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	@RequestMapping(value = "mesdctap", method = RequestMethod.GET)
	public String mesdctap(Model model) {
		User me = UtilSession.getUserInSession();
		model.addAttribute("mesdctaps", manager.getAllDCTAPByProfPrinc(me));
		return "prof-principal/mesdctap";
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(UserSearchCriteria userSearchCriteria, Model model) {
		User user = UtilSession.getUserInSession();
		model.addAttribute("lesClasses",
				manager.getAllClasseByProf(user.getId()));

		model.addAttribute("lesEleves", manager.getAllEleveByClasse());
		model.addAttribute("lesAP", manager.getApByType());
	}

	@RequestMapping(value = "dosearchForClasse", method = RequestMethod.GET)
	public String searchClasse(UserSearchCriteria userSearchCriteria,
			BindingResult bindResult, Model model) {

		if (userSearchCriteria.getQuery() == null
				|| "".equals(userSearchCriteria.getQuery())) {
			bindResult.rejectValue("query", "required",
					"Entrez un critère de recherche valide");
		}
		if (bindResult.hasErrors()) {
			return "prof-principal/index";
		} else {
			model.addAttribute("users",
					manager.searchClasse(userSearchCriteria));
			return "prof-principal/index";
		}
	}
}
