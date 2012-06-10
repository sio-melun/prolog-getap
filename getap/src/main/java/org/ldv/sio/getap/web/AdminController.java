package org.ldv.sio.getap.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.CSV;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.DemandesCSV;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.FormAccueilPerso;
import org.ldv.sio.getap.app.FormAjoutAp;
import org.ldv.sio.getap.app.FormAjoutClasse;
import org.ldv.sio.getap.app.FormAjoutDiscipline;
import org.ldv.sio.getap.app.FormAjoutUser;
import org.ldv.sio.getap.app.FormAjoutUsers;
import org.ldv.sio.getap.app.FormEditUser;
import org.ldv.sio.getap.app.JDBC;
import org.ldv.sio.getap.app.PDF;
import org.ldv.sio.getap.app.StatsPDF;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.UserSearchCriteria;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	@Autowired
	private JDBC jdbc;
	@Autowired
	private PDF pdf;
	@Autowired
	private CSV csv;
	@Autowired
	private StatsPDF statsPdf;
	@Autowired
	private DemandesCSV demandes;

	public void setCsv(CSV csv) {
		this.csv = csv;
	}

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	public void setJdbc(JDBC jdbc) {
		this.jdbc = jdbc;
	}

	public void setPdf(PDF pdf) {
		this.pdf = pdf;
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index(
			@ModelAttribute(value = "formAjoutUsers") FormAjoutUsers form,
			UserSearchCriteria userSearchCriteria, FormAjoutAp formAjout,
			FormAjoutDiscipline formAjoutDis, FormAjoutClasse formAjoutClasse,
			Model model) {
		model.addAttribute("lesAP", manager.getAllAPForAdmin());
		model.addAttribute("lesClasses", manager.getAllClasse());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("lesEleves", manager.getAllEleveByClasse());
		model.addAttribute("lesProfs", manager.getAllProf());
	}

	@RequestMapping(value = "logiciel", method = RequestMethod.GET)
	public void logiciel(UserSearchCriteria userSearchCriteria,
			FormAccueilPerso formAccueilPerso, FormAjoutAp formAjout,
			FormAjoutDiscipline formAjoutDis, FormAjoutClasse formAjoutClasse,
			Model model) {
		model.addAttribute("lesAP", manager.getAllAPForAdmin());
		model.addAttribute("lesClasses", manager.getAllClasse());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
	}

	@RequestMapping(value = "ajoutUser", method = RequestMethod.GET)
	public String ajoutUser(FormAjoutUser formAjout, Model model) {

		model.addAttribute("lesClasses", manager.getAllClasse());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("lesRoles", manager.getAllRole());
		model.addAttribute("nbClasse", manager.countClasses());

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
			User user = null;
			Discipline dis = null;
			System.out.println(classe);
			if (formAjout.getRoleNom().startsWith("prof")) {
				dis = new Discipline(formAjout.getDisciplineId(),
						formAjout.getDisciplineNom());
			}
			if (formAjout.getRoleNom().equals("prof-intervenant")
					|| formAjout.getRoleNom().equals("admin"))
				classe = null;
			if (formAjout.getRoleNom().equals("prof-principal")) {
				user = new User(null, formAjout.getPrenom(),
						formAjout.getNom(), classe, formAjout.getRoleNom(),
						formAjout.getClasse(), dis);
			} else {
				user = new User(null, formAjout.getPrenom(),
						formAjout.getNom(), classe, formAjout.getRoleNom(), dis);
			}

			User userAjoute = manager.addUser(user);

			model.addAttribute("userAjoute", userAjoute);

			return "admin/doajout";
		}
	}

	@RequestMapping(value = "detailUser", method = RequestMethod.GET)
	public String detailUser(@RequestParam("id") String id, Model model) {
		User user = manager.getUserById(Long.valueOf(id));
		model.addAttribute("utilisateur", user);
		model.addAttribute("sesDCTAPeleve", manager.getAllDCTAPByEleve(user));
		model.addAttribute("sesDCTAPprof",
				manager.getAllDCTAPByProfInterv(user));
		Long idUser = user.getId();
		model.addAttribute("etat0", manager.getAllDCTAPByEtat(0, idUser));
		model.addAttribute("etat1", manager.getAllDCTAPByEtat(1, idUser));
		model.addAttribute("etat2", manager.getAllDCTAPByEtat(2, idUser));
		model.addAttribute("etat4", manager.getAllDCTAPByEtat(4, idUser));
		model.addAttribute("etat8", manager.getAllDCTAPByEtat(8, idUser));
		model.addAttribute("etat32", manager.getAllDCTAPByEtat(32, idUser));
		model.addAttribute("etat64", manager.getAllDCTAPByEtat(64, idUser));
		model.addAttribute("etatsup1000",
				manager.getAllDCTAPModifByEtat(idUser));

		return "admin/detailUser";
	}

	@RequestMapping(value = "ajoutAp", method = RequestMethod.GET)
	public String ajoutAp(FormAjoutAp formAjout, Model model) {

		return "admin/ajoutAp";
	}

	@RequestMapping(value = "doajoutAP", method = RequestMethod.POST)
	public String doajoutAP(FormAjoutAp formAjout, BindingResult bindResult,
			Model model) {
		AccPersonalise acc = new AccPersonalise();
		acc.setNom(formAjout.getNom());
		acc.setOrigineEtat(0);
		acc.setIdUser(null);

		manager.addAP(acc);

		return "redirect:/app/admin/logiciel";
	}

	@RequestMapping(value = "editAp", method = RequestMethod.GET)
	public String editAp(@RequestParam("id") String id, FormAjoutAp formAjout,
			Model model) {
		AccPersonalise acc = manager.getAPById(Integer.valueOf(id));
		formAjout.setNom(acc.getNom());
		return "admin/editAp";
	}

	@RequestMapping(value = "doEditAP", method = RequestMethod.POST)
	public String doeditApById(FormAjoutAp formEdit, BindingResult bindResult,
			Model model) {

		if (bindResult.hasErrors()) {
			return "redirect:/app/admin/logiciel";
		} else {

			AccPersonalise acc = manager.getAPById(Integer.valueOf(formEdit
					.getId()));
			acc.setNom(formEdit.getNom());
			manager.upDateAP(acc);

			return "redirect:/app/admin/logiciel";
		}
	}

	@RequestMapping(value = "deleteAP/{id}", method = RequestMethod.GET)
	public String deleteAPById(@PathVariable String id, Model model) {
		AccPersonalise acc = manager.getAPById(Integer.valueOf(id));

		if (!acc.getId().equals(null)) {
			manager.deleteAP(acc);
		}
		return "redirect:/app/admin/logiciel";

	}

	@RequestMapping(value = "ajoutDiscipline", method = RequestMethod.GET)
	public String ajoutDiscipline(FormAjoutDiscipline formAjout, Model model) {

		return "admin/ajoutDiscipline";
	}

	@RequestMapping(value = "doajoutDiscipline", method = RequestMethod.POST)
	public String doajoutDiscipline(FormAjoutDiscipline formAjout,
			BindingResult bindResult, Model model) {

		Discipline dis = new Discipline();
		dis.setNom(formAjout.getNom());

		manager.addDiscipline(dis);

		return "redirect:/app/admin/logiciel";
	}

	@RequestMapping(value = "editDiscipline", method = RequestMethod.GET)
	public String editDiscipline(@RequestParam("id") String id,
			FormAjoutDiscipline formAjout, Model model) {
		Discipline dis = manager.getDisciplineById(Integer.valueOf(id));
		formAjout.setNom(dis.getNom());
		return "admin/editDiscipline";
	}

	@RequestMapping(value = "doEditDiscipline", method = RequestMethod.POST)
	public String doeditDisciplineById(FormAjoutDiscipline formEdit,
			BindingResult bindResult, Model model) {

		if (bindResult.hasErrors()) {
			System.out.println("ERROR");
			return "redirect:/app/admin/logiciel";
		} else {

			Discipline dis = manager.getDisciplineById(Integer.valueOf(formEdit
					.getId()));
			dis.setNom(formEdit.getNom());
			manager.upDateDiscipline(dis);

			return "redirect:/app/admin/logiciel";
		}
	}

	@RequestMapping(value = "deleteDiscipline/{id}", method = RequestMethod.GET)
	public String deleteDisciplineById(@PathVariable String id, Model model) {

		Discipline dis = manager.getDisciplineById(Integer.valueOf(id));

		if (!dis.getNom().equals(null)) {
			manager.deleteDiscipline(dis);
		}
		return "redirect:/app/admin/logiciel";

	}

	@RequestMapping(value = "ajoutClasse", method = RequestMethod.GET)
	public String ajoutAp(FormAjoutClasse formAjout, Model model) {

		return "admin/ajoutClasse";
	}

	@RequestMapping(value = "doajoutClasse", method = RequestMethod.POST)
	public String doajoutClasse(FormAjoutClasse formAjout,
			BindingResult bindResult, Model model) {

		Classe classe = new Classe();
		classe.setNom(formAjout.getNom());
		manager.addClasse(classe);

		return "redirect:/app/admin/logiciel";
	}

	@RequestMapping(value = "editClasse", method = RequestMethod.GET)
	public String editClasse(@RequestParam("id") String id,
			FormAjoutClasse formAjout, Model model) {
		Classe classe = manager.getClasseById(Integer.valueOf(id));
		formAjout.setNom(classe.getNom());
		return "admin/editClasse";
	}

	@RequestMapping(value = "doEditClasse", method = RequestMethod.POST)
	public String doeditClasseById(FormAjoutClasse formEdit,
			BindingResult bindResult, Model model) {

		if (bindResult.hasErrors()) {
			System.out.println("ERROR");
			return "redirect:/app/admin/logiciel";
		} else {

			Classe classe = manager.getClasseById(Integer.valueOf(formEdit
					.getId()));
			classe.setNom(formEdit.getNom());
			manager.upDateClasse(classe);

			return "redirect:/app/admin/logiciel";
		}
	}

	@RequestMapping(value = "deleteClasse/{id}", method = RequestMethod.GET)
	public String deleteClasseById(@PathVariable String id, Model model) {

		Classe classe = manager.getClasseById(Integer.valueOf(id));

		if (!classe.getNom().equals(null)) {
			manager.deleteClasse(classe);
		}
		return "redirect:/app/admin/logiciel";

	}

	@RequestMapping(value = "searchDctapClasse", method = RequestMethod.GET)
	public void searchDctapClasse(UserSearchCriteria userSearchCriteria,
			Model model) {
		model.addAttribute("lesClasses", manager.getAllClasse());
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
					"Entrez un critère de recherche valide");
		}
		if (bindResult.hasErrors()) {
			return "admin/searchUser";
		} else {
			model.addAttribute("users", manager.searchEleve(userSearchCriteria));
			return "admin/dosearchUser";
		}
	}

	@RequestMapping(value = "dosearchProf", method = RequestMethod.GET)
	public String searchProf(UserSearchCriteria userSearchCriteria,
			BindingResult bindResult, Model model) {

		if (userSearchCriteria.getQuery() == null
				|| "".equals(userSearchCriteria.getQuery())) {
			bindResult.rejectValue("query", "required",
					"Entrez un critère de recherche valide");
		}
		if (bindResult.hasErrors()) {
			return "admin/searchProf";
		} else {
			model.addAttribute("users", manager.searchProf(userSearchCriteria));
			return "admin/dosearchUser";
		}
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
			return "admin/searchClasse";
		} else {
			model.addAttribute("users",
					manager.searchClasse(userSearchCriteria));
			return "admin/dosearchUser";
		}
	}

	@RequestMapping(value = "editUser", method = RequestMethod.GET)
	public String editUserById(@RequestParam("id") String id,
			FormEditUser formUser, Model model) {

		System.out.println("TEST id recu :" + formUser.getId());

		User currentUser = manager.getUserById(Long.valueOf(id));
		System.out.println(currentUser);

		formUser.setId(currentUser.getId());
		System.out.println("TEST id : " + currentUser.getId());
		formUser.setNom(currentUser.getNom());
		System.out.println("TEST nom : " + currentUser.getNom());
		formUser.setPrenom(currentUser.getPrenom());
		System.out.println("TEST prenom : " + currentUser.getPrenom());
		System.out.println("TEST role : " + currentUser.getRole());
		formUser.setRole(currentUser.getRole());
		model.addAttribute("fonction", currentUser.getRole());
		if (currentUser.getRole().startsWith("prof")) {
			formUser.setDisciplineId(currentUser.getDiscipline().getId());
		}
		if (currentUser.getRole().equals("eleve")) {
			formUser.setClasseId(currentUser.getClasse().getId());
		}

		model.addAttribute("lesClasses", manager.getAllClasse());
		model.addAttribute("lesRoles", manager.getAllRole());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("mesClasses",
				manager.getAllClasseByProf(currentUser.getId()));
		model.addAttribute("mdp", currentUser.getPass());

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
			Discipline dis = null;
			if (formUser.getRoleNom().startsWith("prof")) {
				dis = new Discipline(formUser.getDisciplineId(),
						formUser.getDisciplineNom());
			}
			userForUpdate.setNom(formUser.getNom());
			userForUpdate.setPrenom(formUser.getPrenom());
			userForUpdate.setRole(formUser.getRoleNom());
			userForUpdate.setDiscipline(dis);
			System.out.println("ROLE : " + formUser.getRoleNom());
			if (formUser.getRoleNom().equals("eleve")) {
				userForUpdate.setClasse(manager.getClasseById(formUser
						.getClasseId()));
			} else if (formUser.getRoleNom().equals("prof-principal")) {
				userForUpdate.setLesClasses(formUser.getClasse());
			}

			manager.updateUser(userForUpdate);

			return "admin/doEditUser";
		}
	}

	@RequestMapping(value = "delUser/{id}", method = RequestMethod.GET)
	public String deleteUserById(@PathVariable String id, Model model) {
		User user = manager.getUserById(Long.valueOf(id));

		if (!user.getId().equals(null)) {
			manager.deleteUser(user);
		}
		return "redirect:/app/admin/index";
	}

	@RequestMapping(value = "ajoutUsers", method = RequestMethod.GET)
	public String ajoutUsers(FormAjoutAp formAjout, FormAjoutUsers file,
			Model model) {
		model.addAttribute(new FormAjoutUsers());
		return "admin/ajoutUsers";
	}

	@RequestMapping(value = "doajouts", method = RequestMethod.POST)
	public String doajouts(
			@ModelAttribute(value = "formAjoutUsers") FormAjoutUsers form,
			BindingResult result, UserSearchCriteria userSearchCriteria,
			FormAjoutAp formAjout, FormAjoutClasse formAjoutClasse,
			FormAjoutDiscipline formAjoutDiscipline, Model model) {
		System.out.println("TEST :" + model);

		if (result.hasErrors())
			return "admin/ajoutUsers";
		else {
			FileOutputStream outputStream = null;
			String filePath = System.getProperty("java.io.tmpdir")
					+ File.separatorChar + form.getFile().getOriginalFilename();
			try {
				outputStream = new FileOutputStream(new File(filePath));
				outputStream.write(form.getFile().getFileItem().get());
				outputStream.close();
				jdbc.feedBDD(filePath);
			} catch (Exception e) {
				System.out.println("Error while saving file : ");
				e.printStackTrace();
				return "admin/index";
			}
		}
		return "redirect:/app/admin/index";
	}

	@RequestMapping(value = "exportUserPdf")
	public void exportUserPdf(HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment;filename=utilisateurs.pdf");
		pdf.export(response);
	}

	@RequestMapping(value = "exportStats/{id}", method = RequestMethod.GET)
	public void exportStats(@PathVariable String id,
			HttpServletResponse response) {
		User user = manager.getUserById(Long.valueOf(id));
		List<DemandeConsoTempsAccPers> dctap = manager.getAllDCTAPByEleve(user);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=stats"
				+ user.getNom() + ".pdf");
		statsPdf.export(response, user.getId(), dctap);
	}

	@RequestMapping(value = "exportDemandeCsv/{id}", method = RequestMethod.GET)
	public void exportDemandeCsv(@PathVariable String id,
			HttpServletResponse response) {
		User user = manager.getUserById(Long.valueOf(id));
		List<DemandeConsoTempsAccPers> dctap = manager.getAllDCTAPByEleve(user);
		response.setContentType("application/csv");
		response.setHeader("Content-Disposition",
				"attachment;filename=demandes" + user.getNom() + ".csv");
		demandes.export(response, dctap);
	}

	@RequestMapping(value = "exportUserCsv")
	public void exportUserCsv(HttpServletResponse response) {
		response.setContentType("text/x-csv; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=eleves.csv");
		csv.export(response);
	}

	@RequestMapping(value = "doEditPass", method = RequestMethod.POST)
	public String doEditPass(FormEditUser formUser, BindingResult bindResult,
			Model model) {
		System.out.println("TEST :" + model);

		if (bindResult.hasErrors())
			return "admin/ajoutUsers";
		else {
			User user = manager.getUserById(Long.valueOf(formUser.getId()));
			manager.updatePass(user);
		}
		return "redirect:/app/admin/index";
	}

	@RequestMapping(value = "doPerso", method = RequestMethod.POST)
	public String doPerso(FormAccueilPerso form, Model model) {
		FileOutputStream img, logo = null;
		String imgPath = System.getProperty("java.io.tmpdir")
				+ form.getImg().getOriginalFilename();
		String logoPath = System.getProperty("java.io.tmpdir")
				+ form.getLogo().getOriginalFilename();

		try {
			List<String> tab = manager.getInfoAccueil();
			try {
				img = new FileOutputStream(new File(imgPath));
				img.write(form.getImg().getFileItem().get());
				img.close();
			} catch (FileNotFoundException p) {
				imgPath = tab.get(0);
			}
			try {
				logo = new FileOutputStream(new File(logoPath));
				logo.write(form.getLogo().getFileItem().get());
				logo.close();
			} catch (FileNotFoundException p) {
				logoPath = tab.get(1);

			}
			String titre;
			String texte;
			if (!form.getTitre().equals(null) && !form.getTitre().equals("")) {
				System.out.println(form.getTitre());
				titre = form.getTitre();
			} else {
				titre = tab.get(2);
			}
			if (!form.getTexte().equals(null) && !form.getTexte().equals("")) {
				System.out.println(form.getTexte());
				texte = form.getTexte();
			} else {
				texte = tab.get(3);
			}

			// String[] urlimg = imgPath.split("\\");
			// System.out.println(urlimg);
			// String split = urlimg[-1];
			// System.out.println(split);

			manager.addAccueil(imgPath, logoPath, titre, texte);
		} catch (Exception e) {
			System.out.println("Error while saving file : ");
			e.printStackTrace();
		}
		return "redirect:/app/admin/logiciel";
	}
}
