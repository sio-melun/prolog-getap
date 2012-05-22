package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.FormEditProfil;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/profil/*")
public class Profil {

	@Autowired
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(Model model) {
		User me = UtilSession.getUserInSession();
		model.addAttribute("user", me);
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editUserById(@RequestParam("id") String id,
			FormEditProfil formUser, Model model) {
		User me = UtilSession.getUserInSession();
		System.out.println("TEST id recu :" + me.getId());

		User currentUser = manager.getUser(Long.valueOf(id));

		System.out.println("User : " + currentUser);
		if (currentUser.getId().equals(UtilSession.getUserInSession().getId())) {
			formUser.setLogin(currentUser.getLogin());
			formUser.setMail(currentUser.getMail());

			return "profil/edit";
		}
		return "profil/index";
	}

	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public String doeditUserById(FormEditProfil formUser,
			BindingResult bindResult, Model model) {

		// java.sql.Date.valueOf(formDctap.getDateAction());
		if (bindResult.hasErrors()) {
			return "profil/edit";
		} else {
			User me = UtilSession.getUserInSession();
			User user = manager.getUser(Long.valueOf(me.getId()));

			// valorise l'objet de la base à partir du bean de vue
			user.setLogin(formUser.getLogin());
			if (formUser.getOldPass().equals(user.getPass())
					&& formUser.getFirstPass().equals(formUser.getSecondPass())) {
				user.setPass(formUser.getFirstPass());
			} else {
				user.setPass(user.getPass());
			}
			if (formUser.getMail().equals(null)
					|| formUser.getMail().equals("")) {
				user.setMail(null);
			} else {
				user.setMail(formUser.getMail());
			}
			manager.updateProfil(user);

			return "redirect:/app/profil/index";
		}
	}

}
