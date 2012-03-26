package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.FormAjoutUser;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
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
	public void index() {

	}

	@RequestMapping(value = "ajoutUser", method = RequestMethod.GET)
	public String ajoutUser(FormAjoutUser formAjout, Model model) {

		model.addAttribute("lesClasses", manager.getAllClasse());

		return "admin/ajoutUser";
	}

	@RequestMapping(value = "doajout", method = RequestMethod.POST)
	public String doajoutUser(FormAjoutUser formAjout,
			BindingResult bindResult, Model model) {
		System.out.println("TEST :" + formAjout.getId());
		System.out.println("TEST classe :" + formAjout.classe());
		System.out.println("TEST :" + model);

		if (bindResult.hasErrors())
			return "admin/ajoutUser";
		else {
			Classe classe = manager.getClasseById(formAjout.getClasseId());
			System.out.println(classe);

			User user = new User(formAjout.getId(), formAjout.getPrenom(),
					formAjout.getNom(), classe, formAjout.getRole());

			manager.addUser(user);

			return "redirect:/app/admin/index";
		}
	}

}
