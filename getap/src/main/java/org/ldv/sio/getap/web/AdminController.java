package org.ldv.sio.getap.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.AnneeScolaire;
import org.ldv.sio.getap.app.CSV;
import org.ldv.sio.getap.app.Classe;
import org.ldv.sio.getap.app.ClasseStats;
import org.ldv.sio.getap.app.DateStats;
import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.DemandesCSV;
import org.ldv.sio.getap.app.Discipline;
import org.ldv.sio.getap.app.ExportPDF;
import org.ldv.sio.getap.app.FormAccueilPerso;
import org.ldv.sio.getap.app.FormAjoutAp;
import org.ldv.sio.getap.app.FormAjoutClasse;
import org.ldv.sio.getap.app.FormAjoutDiscipline;
import org.ldv.sio.getap.app.FormAjoutUsers;
import org.ldv.sio.getap.app.FormEditUser;
import org.ldv.sio.getap.app.ImportFromSqlFile;
import org.ldv.sio.getap.app.LoginInfo;
import org.ldv.sio.getap.app.ProfStats;
import org.ldv.sio.getap.app.StatsPDF;
import org.ldv.sio.getap.app.StatsProfesseurCSV;
import org.ldv.sio.getap.app.TypeStats;
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
	@Qualifier("DBServiceManager")
	private IFManagerGeTAP manager;

	@Autowired
	private ImportFromSqlFile jdbc;
	@Autowired
	private ExportPDF pdf;
	@Autowired
	private CSV csv;
	@Autowired
	private StatsPDF statsPdf;
	@Autowired
	private DemandesCSV demandes;
	@Autowired
	private StatsProfesseurCSV demandesStatsProfesseur;

	public void setCsv(CSV csv) {
		this.csv = csv;
	}

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	public void setJdbc(ImportFromSqlFile jdbc) {
		this.jdbc = jdbc;
	}

	public void setPdf(ExportPDF pdf) {
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
			HttpServletRequest httpRequest, Model model, HttpSession session) {
		model.addAttribute("lesAP", manager.getAllAPForAdmin());
		model.addAttribute("lesClasses", manager.getAllClasses());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("lesEleves", manager.getAllEleveByClasse());
		model.addAttribute("lesProfs", manager.getAllProf());
		model.addAttribute("firstClasse", manager.getFirstIdClasse());

		if (session.getAttribute("eleveDeleted") != null) {
			session.removeAttribute("eleveDeleted");
			model.addAttribute("eleveDeleted", "DELETED");
		}

	}

	@RequestMapping(value = "logiciel", method = RequestMethod.GET)
	public void logiciel(UserSearchCriteria userSearchCriteria,
			FormAccueilPerso formAccueilPerso, FormAjoutAp formAjout,
			FormAjoutDiscipline formAjoutDis, FormAjoutClasse formAjoutClasse,
			Model model) {
		model.addAttribute("lesAP", manager.getAllAPForAdmin());
		model.addAttribute("lesClasses", manager.getAllClasses());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
	}

	@RequestMapping(value = "ajoutUser", method = RequestMethod.GET)
	public String ajoutUser(FormEditUser formAjout, Model model) {

		model.addAttribute("lesClasses", manager.getAllClasses());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("lesRoles", manager.getAllRole());
		model.addAttribute("nbClasse", manager.countClasses());

		return "admin/ajoutUser";
	}

	@RequestMapping(value = "doajout", method = RequestMethod.POST)
	public String doajoutUser(FormEditUser formAjout, BindingResult bindResult,
			Model model) {

		if (bindResult.hasErrors())
			return "admin/ajoutUser";
		else {
			Classe classe = manager.getClasseById(formAjout.getClasseId());
			User user = null;
			Discipline dis = null;
			if (formAjout.getRoleNom().startsWith("prof")) {
				dis = new Discipline(formAjout.getDisciplineId(),
						formAjout.getDisciplineNom());
			}
			if (formAjout.getRoleNom().equals("prof-intervenant")
					|| formAjout.getRoleNom().equals("admin"))
				classe = null;
			if (formAjout.getRoleNom().equals("prof-principal")) {

				user = new User(null, formAjout.getPrenom(),
						formAjout.getNom(), null, formAjout.getRoleNom(),
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
		List<LoginInfo> loginInfo = manager.getLoginInfoById(id);
		model.addAttribute("lastlog", loginInfo.get(0).getLastlog());
		model.addAttribute("countlog", loginInfo.get(0).getCountlog());

		model.addAttribute("utilisateur", user);
		model.addAttribute("sesDCTAPeleve", manager.getAllDVCTAPByEleve(user));
		model.addAttribute("sesDCTAPprof",
				manager.getAllDVCTAPByProfInterv(user));
		Long idUser = user.getId();
		model.addAttribute("etat0", manager.getAllDVCTAPByEtat(0, idUser));
		model.addAttribute("etat1", manager.getAllDVCTAPByEtat(1, idUser));
		model.addAttribute("etat2", manager.getAllDVCTAPByEtat(2, idUser));
		model.addAttribute("etat4", manager.getAllDVCTAPByEtat(4, idUser));
		model.addAttribute("etat8", manager.getAllDVCTAPByEtat(8, idUser));
		model.addAttribute("etat32", manager.getAllDVCTAPByEtat(32, idUser));
		model.addAttribute("etat64", manager.getAllDVCTAPByEtat(64, idUser));
		model.addAttribute("etatsup1000",
				manager.getAllDVCTAPModifByEtat(idUser));

		return "admin/detailUser";
	}

	@RequestMapping(value = "statsProfesseurs", method = RequestMethod.GET)
	public String statsProfesseurs(
			@RequestParam(value = "annee", required = false, defaultValue = "default") String annee,
			Model model) {

		List<AnneeScolaire> allYears = manager.getAllYearsForStatsProf();
		model.addAttribute("allYears", allYears);

		if (annee.equals("default")) {
			annee = org.ldv.sio.getap.utils.UtilSession
					.getAnneeScolaireInSession();
		}

		// Récupération des AP des professeurs (personnel)
		List<ProfStats> lesProfStats = manager.getAllAPForEachProf(annee);
		model.addAttribute("eachProf", lesProfStats);

		// Récupération des AP globaux des professeurs (totaux)
		List<Integer> statsAPProf = manager.getAllAPForStatsProf(annee);
		model.addAttribute("demandeTTProfs", statsAPProf.get(0));
		model.addAttribute("demandeValProfs", statsAPProf.get(1));
		model.addAttribute("demandeAttProfs", statsAPProf.get(2));
		model.addAttribute("demandeRefProfs", statsAPProf.get(3));

		// Récupérer l'année visualisée.
		model.addAttribute("anneeCourante", annee);

		return "admin/statsProfesseurs";
	}

	@RequestMapping(value = "statsClasse", method = RequestMethod.GET)
	public String statsClasse(
			@RequestParam(value = "idClasse", required = false, defaultValue = "default") String idClasse,
			@RequestParam(value = "annee", required = false, defaultValue = "default") String annee,
			Model model) {

		List<AnneeScolaire> allYears = manager.getAllYearsForClasseStats();
		model.addAttribute("allYears", allYears);

		if (annee.equals("default")) {

			annee = org.ldv.sio.getap.utils.UtilSession
					.getAnneeScolaireInSession();
		}

		if (idClasse.equals("default")) {

			idClasse = String.valueOf(manager.getFirstIdClasse());

		}

		List<ClasseStats> profsParClasseStats = manager
				.getAllProfesseursForOneClasse((idClasse), annee);
		model.addAttribute("eachProfForEachClasse", profsParClasseStats);

		List<Integer> statsTotalEleveByClasse = manager.getAlldctapByClasse(
				idClasse, annee);
		model.addAttribute("countapTotalElevesByClasse",
				statsTotalEleveByClasse.get(0));
		model.addAttribute("dctapvalideTotalElevesByClasse",
				statsTotalEleveByClasse.get(1));
		model.addAttribute("dctapattenteTotalElevesByClasse",
				statsTotalEleveByClasse.get(2));
		model.addAttribute("dctaprefuseTotalElevesByClasse",
				statsTotalEleveByClasse.get(3));

		List<ClasseStats> allClasses = manager.getAllClassesForStats();
		model.addAttribute("allClasses", allClasses);

		List<ClasseStats> classeStats = manager.getAllAPByIdClasse(idClasse,
				annee);
		model.addAttribute("classeStats", classeStats);

		int intIDClasse = Integer.parseInt(idClasse);
		String nomClasse = manager.getClasseById(intIDClasse).getNom();
		model.addAttribute("nomClasse", nomClasse);

		model.addAttribute("idClasseCourante", idClasse);

		model.addAttribute("anneeCourante", annee);

		return "admin/statsClasse";
	}

	@RequestMapping(value = "statsTypes", method = RequestMethod.GET)
	public String statsTypes(Model model) {

		List<TypeStats> lesTypeStats = manager.getAllAPForEachType();
		model.addAttribute("eachType", lesTypeStats);

		return "admin/statsTypes";
	}

	@RequestMapping(value = "statsDate", method = RequestMethod.GET)
	public String statsDate(
			@RequestParam(value = "annee", required = false, defaultValue = "default") String annee,
			@RequestParam(value = "mois", required = false, defaultValue = "default") String numMois,
			Model model) {

		if (annee.equals("default")) {
			annee = org.ldv.sio.getap.utils.UtilSession
					.getAnneeScolaireInSession();
		}

		if (numMois.equals("default")) {
			numMois = "" + manager.getFirstMois(annee) + "";
		}

		List<DateStats> typeApParMois = manager.getAllDemandeByMois(numMois,
				annee);
		model.addAttribute("typeApParMois", typeApParMois);

		List<DateStats> allMois = manager.getAllMois(annee);
		model.addAttribute("tousMois", allMois);

		model.addAttribute("moisCourante", numMois);

		return "admin/statsDate";
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
		model.addAttribute("lesClasses", manager.getAllClasses());
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

		String queryNomEleve = userSearchCriteria.getQuery();
		if (queryNomEleve == null || "".equals(queryNomEleve)) {
			bindResult.rejectValue("query", "required",
					"Entrez un critère de recherche valide");
		}
		// if (bindResult.hasErrors()) {
		// return "redirect:/app/admin/index";
		//
		// } else {
		model.addAttribute("users", manager.searchEleve(queryNomEleve));
		return "admin/dosearchUser";
		// }
	}

	@RequestMapping(value = "dosearchProf", method = RequestMethod.GET)
	public String searchProf(UserSearchCriteria userSearchCriteria,
			BindingResult bindResult, Model model) {

		String queryNomProf = userSearchCriteria.getQuery();
		if (queryNomProf == null || "".equals(queryNomProf)) {
			bindResult.rejectValue("query", "required",
					"Entrez un critère de recherche valide");
		}
		// if (bindResult.hasErrors()) {
		// return "redirect:/app/admin/index";
		// } else {
		model.addAttribute("users", manager.searchProf(queryNomProf));
		return "admin/dosearchUser";
		// }
	}

	@RequestMapping(value = "dosearchForClasse", method = RequestMethod.GET)
	public String searchClasse(UserSearchCriteria userSearchCriteria,
			BindingResult bindResult, Model model) {

		String queryClasse = userSearchCriteria.getQuery();
		if (queryClasse == null || "".equals(queryClasse)) {
			bindResult.rejectValue("query", "required",
					"Entrez un critère de recherche valide");
		}
		if (bindResult.hasErrors()) {
			return "redirect:/app/admin/index";
		} else {
			model.addAttribute("users", manager.searchClasse(queryClasse));
			return "admin/dosearchUser";
		}
	}

	@RequestMapping(value = "editUser", method = RequestMethod.GET)
	public String editUserById(@RequestParam("id") String id,
			FormEditUser formUser, Model model) {

		// System.out.println("TEST id recu :" + formUser.getId());

		User currentUser = manager.getUserById(Long.valueOf(id));
		System.out.println(currentUser);

		formUser.setId(currentUser.getId());
		System.out.println("editUserById : id = " + currentUser.getId());
		formUser.setNom(currentUser.getNom());
		System.out.println("editUserById : nom = " + currentUser.getNom());
		formUser.setPrenom(currentUser.getPrenom());
		formUser.setRole(currentUser.getRole());
		model.addAttribute("fonction", currentUser.getRole());
		if (currentUser.getRole().startsWith("prof")) {
			formUser.setDisciplineId(currentUser.getDiscipline().getId());
		}
		if (currentUser.getRole().equals("eleve")) {
			formUser.setClasseId(currentUser.getClasse().getId());
		}

		model.addAttribute("lesClasses", manager.getAllClasses());
		model.addAttribute("lesRoles", manager.getAllRole());
		model.addAttribute("lesDisciplines", manager.getAllDiscipline());
		model.addAttribute("mesClasses",
				manager.getAllClasseByProf(currentUser.getId()));
		// model.addAttribute("mdp", currentUser.getPass());
		model.addAttribute("user", currentUser);

		return "admin/editUser";
	}

	@RequestMapping(value = "doEditUser", method = RequestMethod.POST)
	public String doeditUserById(FormEditUser formUser,
			BindingResult bindResult, Model model) {
		// System.out.println("TEST :" + formUser.getId());
		// System.out.println("TEST :" + model);
		// System.out.println("TEST role :" + formUser.getRole());

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
			// System.out.println("ROLE : " + formUser.getRoleNom());
			if (formUser.getRoleNom().equals("eleve")) {
				userForUpdate.setClasse(manager.getClasseById(formUser
						.getClasseId()));
			} else if (formUser.getRoleNom().equals("prof-principal")) {
				userForUpdate.setLesClasses(formUser.getClasse());
			}

			manager.updateUser(userForUpdate);
			return "redirect:/app/admin/index";
		}
	}

	@RequestMapping(value = "delUser/{id}", method = RequestMethod.GET)
	public String deleteUserById(@PathVariable String id, Model model,
			HttpSession session) {

		User user = manager.getUserById(Long.valueOf(id));

		if (!user.getId().equals(null)) {
			manager.deleteUser(user);
			session.setAttribute("eleveDeleted", "DELETED");
		}
		return "redirect:/app/admin/dosearchProf?query=";
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
				"attachment;filename=eleves.pdf");
		pdf.export(response, "eleve");
	}

	@RequestMapping(value = "exportProfPdf")
	public void exportProfPdf(HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment;filename=professeurs.pdf");
		pdf.export(response, "prof-principal");
	}

	@RequestMapping(value = "exportStats/{id}", method = RequestMethod.GET)
	public void exportStats(@PathVariable String id,
			HttpServletResponse response) {
		List<DemandeValidationConsoTempsAccPers> dctaps;
		User user = manager.getUserById(Long.valueOf(id));
		if (user.getRole().equals("prof-principal")) {
			dctaps = manager.getAllDVCTAPByProfPrinc(user);
		} else if (user.getRole().equals("prof-intervenant")) {
			dctaps = manager.getAllDVCTAPByProfInterv(user);
		} else /* user.getRole().equals("eleve") */{
			dctaps = manager.getAllDVCTAPByEleve(user);
		}
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=stats"
				+ user.getNom() + ".pdf");
		statsPdf.export(response, user.getId(), dctaps);
	}

	@RequestMapping(value = "exportDemandeCsv/{id}", method = RequestMethod.GET)
	public void exportDemandeCsv(@PathVariable String id,
			HttpServletResponse response) {
		List<DemandeValidationConsoTempsAccPers> dctaps;
		User user = manager.getUserById(Long.valueOf(id));
		if (user.getRole().equals("prof-principal")) {
			dctaps = manager.getAllDVCTAPByProfPrinc(user);
		} else if (user.getRole().equals("prof-intervenant")) {
			dctaps = manager.getAllDVCTAPByProfInterv(user);
		} else /* user.getRole().equals("eleve") */{
			dctaps = manager.getAllDVCTAPByEleve(user);
		}
		response.setContentType("application/csv");
		response.setHeader("Content-Disposition",
				"attachment;filename=demandes" + user.getNom().trim() + ".csv");
		demandes.export(response, dctaps);
	}

	@RequestMapping(value = "exportStatsProfesseurCSV", method = RequestMethod.GET)
	public void exportStatsProfesseurCSV(HttpServletResponse response) {

		String annee = org.ldv.sio.getap.utils.UtilSession
				.getAnneeScolaireInSession();

		List<ProfStats> lesProfStats = manager.getAllAPForEachProf(annee);

		response.setContentType("application/csv");
		response.setHeader("Content-Disposition",
				"attachment;filename=statsProfesseur.csv");
		demandesStatsProfesseur.export(response, lesProfStats);
	}

	@RequestMapping(value = "exportUserCsv")
	public void exportUserCsv(HttpServletResponse response) {
		response.setContentType("text/x-csv; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=eleve.csv");
		csv.export(response, "eleve");
	}

	@RequestMapping(value = "exportUserCsvProf")
	public void exportUserCsvProf(HttpServletResponse response) {
		response.setContentType("text/x-csv; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=prof-principal.csv");
		csv.export(response, "prof-principal");
	}

	@RequestMapping(value = "doEditPass", method = RequestMethod.POST)
	public String doEditPass(FormEditUser formUser, BindingResult bindResult,
			Model model) {
		// System.out.println("TEST :" + model);

		if (bindResult.hasErrors())
			return "admin/ajoutUsers";
		else {
			User user = manager.getUserById(Long.valueOf(formUser.getId()));
			manager.updatePass(user);
			return "redirect:/app/admin/index";
		}
	}

	@RequestMapping(value = "doPerso", method = RequestMethod.POST)
	public String doPerso(FormAccueilPerso form, Model model) {
		FileOutputStream img, logo = null;
		String imgPath = System.getProperty("java.io.tmpdir")
				+ form.getImg().getOriginalFilename();
		String logoPath = System.getProperty("java.io.tmpdir")
				+ form.getLogo().getOriginalFilename();

		try {
			List<List<String>> tab = manager.getParameter();
			try {
				img = new FileOutputStream(new File(imgPath));
				img.write(form.getImg().getFileItem().get());
				img.close();
			} catch (FileNotFoundException p) {
				imgPath = tab.get(0).get(0);
			}
			try {
				logo = new FileOutputStream(new File(logoPath));
				logo.write(form.getLogo().getFileItem().get());
				logo.close();
			} catch (FileNotFoundException p) {
				logoPath = tab.get(1).get(0);

			}
			String titre;
			String texte;
			if (!form.getTitre().equals(null) && !form.getTitre().equals("")) {
				System.out.println(form.getTitre());
				titre = form.getTitre();
			} else {
				titre = tab.get(2).get(0);
			}
			if (!form.getTexte().equals(null) && !form.getTexte().equals("")) {
				System.out.println(form.getTexte());
				texte = form.getTexte();
			} else {
				texte = tab.get(3).get(0);
			}

			// String[] urlimg = imgPath.split("\\");
			// System.out.println(urlimg);
			// String split = urlimg[-1];
			// System.out.println(split);

			manager.updateAccueil(imgPath, logoPath, titre, texte);
		} catch (Exception e) {
			System.out.println("Error while saving file : ");
			e.printStackTrace();
		}
		return "redirect:/app/admin/logiciel";
	}
}
