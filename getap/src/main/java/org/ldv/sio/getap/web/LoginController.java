package org.ldv.sio.getap.web;

import java.util.HashMap;
import java.util.List;

import org.ldv.sio.getap.app.FormAjoutAp;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserLoginCriteria;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFHauthLoginService;
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
 * Web controller for application actions.
 */
@Controller
@RequestMapping("/login/*")
public class LoginController {

	@Autowired
	@Qualifier("serviceAuth")
	private IFHauthLoginService hauthLoginService;

	@Autowired
	@Qualifier("DBServiceManager")
	private IFManagerGeTAP manager;

	public void setHauthLoginService(IFHauthLoginService hauthLoginService) {
		this.hauthLoginService = hauthLoginService;
	}

	@RequestMapping(value = "apropos", method = RequestMethod.GET)
	public String apropos(UserLoginCriteria userLoginCriteria) {

		return "login/apropos";
	}

	/**
	 * Default action, displays the login page.
	 * 
	 * @param UserLoginCriteria
	 *            The criteria to authenticate
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(UserLoginCriteria userSearchCriteria, Model model) {
		addParamAcc(model);
	}

	/**
	 * Valide user puis l'authentifie via le bean injecté hauthLoginService
	 * 
	 * @param userLoginCriteria
	 *            The criteria to validate for
	 * @param bindResult
	 *            Holds userLoginCriteria validation errors
	 * @param model
	 *            Holds the resulting user (is authenticate)
	 * @return Success or index view
	 */
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
	public String authenticate(FormAjoutAp formAjout,
			UserLoginCriteria userLoginCriteria, BindingResult bindResult,
			Model model, UserSearchCriteria userSearchCriteria) {

		addParamAcc(model);

		if (userLoginCriteria.getLogin() == null
				|| "".equals(userLoginCriteria.getLogin().trim())) {
			bindResult.rejectValue("login", "required",
					"Un identifiant est attendu !");
			return "login/authenticate";
		}
		if (bindResult.hasErrors()) {
			return "login/index";
		} else {
			User user = hauthLoginService.getAuthUser(userLoginCriteria);
			if (user == null) {
				bindResult.rejectValue("login", "required",
						"Entrez un identifiant valide");
				return "login/authenticate";
			}
			UtilSession.setUserInSession(user);
			manager.logUser(user);
			String anneeScolaire = manager.getCurrentAnneeScolaire();
			UtilSession.setAnneeScolaireInSession(anneeScolaire);
			model.addAttribute("userAuth", user);
			User userIn = UtilSession.getUserInSession();

			if (userIn.getMail() == null
					&& userIn.getRole().equals("prof-principal")) {
				return "redirect:/app/" + userIn.getRole() + "/mail";
			}

			return "redirect:/app/" + userIn.getRole() + "/index";
		}
	}

	/**
	 * supprime user de la session, et retourne au login
	 * 
	 * @param userLoginCriteria
	 *            (pour fournir cette instance à la vue 'login/index')
	 * @return Success view
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(UserLoginCriteria userLoginCriteria, Model model) {
		UtilSession.setUserInSession(null);

		addParamAcc(model);

		return "login/index";
	}

	// methodes

	public void addParamAcc(Model model) {
		List<HashMap<String, String>> infos = manager.getParameter();

		model.addAttribute("img", infos.get(0).get("img"));
		model.addAttribute("logo", infos.get(1).get("logo"));
		model.addAttribute("titre", infos.get(2).get("titre"));
		model.addAttribute("texte", infos.get(3).get("texte"));

	}

}
