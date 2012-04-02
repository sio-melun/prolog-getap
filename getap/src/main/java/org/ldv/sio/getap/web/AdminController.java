package org.ldv.sio.getap.web;

import javax.swing.JOptionPane;

import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.FormAjoutUser;
import org.ldv.sio.getap.app.FormEditUser;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
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
		model.addAttribute("lesRoles", manager.getAllRole());

		return "admin/ajoutUser";
	}

	@RequestMapping(value = "doajout", method = RequestMethod.POST)
	public String doajoutUser(FormAjoutUser formAjout,
			BindingResult bindResult, Model model) {
		System.out.println("TEST :" + formAjout.getId());
		System.out.println("TEST classe ID et Nom :" + formAjout.classe());
		System.out.println("TEST role :" + formAjout.getRoleNom());
		System.out.println("TEST :" + model);

		if (bindResult.hasErrors())
			return "admin/ajoutUser";
		else {
			Classe classe = manager.getClasseById(formAjout.getClasseId());
			System.out.println(classe);

			if (formAjout.getRoleNom().equals("prof-intervenant")
					|| formAjout.getRoleNom().equals("admin"))
				classe = null;

			User user = new User(formAjout.getId(), formAjout.getPrenom(),
					formAjout.getNom(), classe, formAjout.getRoleNom());

			manager.addUser(user);

			return "redirect:/app/admin/index";
		}
	}

	@RequestMapping(value = "searchUser", method = RequestMethod.GET)
	public void searchUser(UserSearchCriteria userSearchCriteria) {

	}

	/**
	 * @param userSearchCriteria
	 * @param bindResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "dosearchUser", method = RequestMethod.GET)
	public String search(UserSearchCriteria userSearchCriteria,
			BindingResult bindResult, Model model) {

		if (userSearchCriteria.getQuery() == null
				|| "".equals(userSearchCriteria.getQuery())) {
			bindResult.rejectValue("query", "required",
					"Please enter valid search criteria");
		}
		if (bindResult.hasErrors()) {
			return "admin/searchUser";
		} else {
			model.addAttribute("users", manager.search(userSearchCriteria));
			return "admin/dosearchUser";
		}
	}

	@RequestMapping(value = "editUser", method = RequestMethod.GET)
	public String editUserById(@RequestParam("id") String id,
			FormEditUser formUser, Model model) {

		System.out.println("TEST id recu :" + formUser.getId());

		User currentUser = manager.getUserById(Long.valueOf(id));

		formUser.setId(currentUser.getId());
		formUser.setNom(currentUser.getNom());
		formUser.setPrenom(currentUser.getPrenom());
		if (!currentUser.getRole().equals("prof-intervenant")
				|| !currentUser.getRole().equals("admin")) {
			formUser.setClasseId(currentUser.getClasse().getId());
		}
		formUser.setRole(currentUser.getRole());

		System.out.println("TEST role : " + currentUser.getRole());

		model.addAttribute("lesClasses", manager.getAllClasse());
		model.addAttribute("lesRoles", manager.getAllRole());

		return "admin/editUser";
	}

	@RequestMapping(value = "doEditUser", method = RequestMethod.POST)
	public String doeditUserById(FormEditUser formUser,
			BindingResult bindResult, Model model) {
		System.out.println("TEST :" + formUser.getId());
		System.out.println("TEST :" + model);
		System.out.println("TEST role :" + formUser.getRole());

		if (bindResult.hasErrors()) {
			return "admin/editUser";
		} else {

			User userForUpdate = manager.getUserById(Long.valueOf(formUser
					.getId()));

			userForUpdate.setNom(formUser.getNom());
			userForUpdate.setPrenom(formUser.getPrenom());
			if (!formUser.getRole().equals("prof-intervenant")
					|| !formUser.getRole().equals("admin")) {
				userForUpdate.setClasse(manager.getClasseById(formUser
						.getClasseId()));
			}
			userForUpdate.setRole(formUser.getRole());

			return "redirect:/app/admin/searchUser";
		}
	}

	@RequestMapping(value = "delUser/{id}", method = RequestMethod.GET)
	public String deleteUserById(@PathVariable String id, Model model) {
		int del = JOptionPane.showConfirmDialog(null,
				"Voulez-vous continuer cette suppression ?", "Vérification",
				JOptionPane.YES_NO_OPTION);
		User user = manager.getUserById(Long.valueOf(id));

		if (user.getId().equals(null))
			return "redirect:/app/admin/index";
		else if (del == 0)
			manager.deleteUser(user);
		else if (del == 1)
			return "redirect:/app/admin/dosearchUser?query=" + user.getNom();

		return "redirect:/app/admin/searchUser";
	}
}
